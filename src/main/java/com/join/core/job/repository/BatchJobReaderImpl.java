package com.join.core.job.repository;

import com.join.core.common.constant.DayType;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.job.domain.BatchJob;
import com.join.core.job.domain.BatchJobReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class BatchJobReaderImpl implements BatchJobReader {

    private final BatchJobRepository batchJobRepository;

    @Override
    public boolean existsByStudyStudyTokenAndDayAndTime(String studyToken, DayType day, LocalTime time) {
        return batchJobRepository.existsByStudyStudyTokenAndDayAndTime(studyToken, day, time);
    }

    public void validateUniqueBatchJob(String studyToken, DayType day, LocalTime time) {
        if (existsByStudyStudyTokenAndDayAndTime(studyToken, day, time.withNano(0))) {
            throw new BadRequestException(ErrorCode.BATCHJOB_ALREADY_EXISTS);
        }
    }

    @Override
    public BatchJob getBatchJobById(Long batchJobId) {
        return batchJobRepository.findById(batchJobId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.BATCHJOB_NOT_FOUND));
    }

}
