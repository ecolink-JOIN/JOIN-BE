package com.join.core.job.domain;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.job.dto.request.BatchJobRequest;
import com.join.core.job.dto.request.BatchJobUpdateRequest;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BatchJobService {

    private final BatchJobReader batchJobReader;
    private final BatchJobStore batchJobStore;
    private final StudyReader studyReader;

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

}