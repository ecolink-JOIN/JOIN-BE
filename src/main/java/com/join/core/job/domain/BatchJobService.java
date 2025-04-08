package com.join.core.job.domain;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.job.dto.request.BatchJobRequest;
import com.join.core.job.dto.request.BatchJobUpdateRequest;
import com.join.core.job.dto.response.BatchJobResponse;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchJobService {

    private final BatchJobReader batchJobReader;
    private final BatchJobStore batchJobStore;
    private final StudyReader studyReader;
    private final BatchJobDeleter batchJobDeleter;

    @Transactional
    public BatchJob createBatchJob(BatchJobRequest request, Long userId) {
        Study study = studyReader.getStudyByToken(request.getStudyToken());

        if (!study.isWriter(userId)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        batchJobReader.validateUniqueBatchJob(request.getStudyToken(), request.getDay(), request.getTime());

        return batchJobStore.store(request.toEntity(study));
    }

    @Transactional
    public BatchJob updateBatchJob(Long batchJobId, BatchJobUpdateRequest updateRequest, Long userId) {
        BatchJob batchJob = batchJobReader.getBatchJobById(batchJobId);

        if (!batchJob.getStudy().isWriter(userId)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        batchJobReader.validateUniqueBatchJob(updateRequest.getStudyToken(), updateRequest.getDay(), updateRequest.getTime());

        batchJob.update(updateRequest.getContent(), updateRequest.getDay(), updateRequest.getTime());
        return batchJobStore.store(batchJob);
    }

    @Transactional(readOnly = true)
    public List<BatchJobResponse> getBatchJobsByStudy(String studyToken, Long userId) {
        List<BatchJob> batchJobs = batchJobReader.getBatchJobsByStudyToken(studyToken);

        return batchJobs.stream()
                .map(job -> new BatchJobResponse(job.getId(), job.getContent(), job.getDay(), job.getTime()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBatchJob(Long batchJobId, Long userId) {
        BatchJob batchJob = batchJobReader.getBatchJobById(batchJobId);

        if (!batchJob.getStudy().isWriter(userId)) {
            throw new BadRequestException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        batchJobDeleter.deleteBatchJob(batchJob);
    }


}