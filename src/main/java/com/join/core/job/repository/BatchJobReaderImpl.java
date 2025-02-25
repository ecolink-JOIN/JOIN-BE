package com.join.core.job.repository;

import com.join.core.common.constant.DayType;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.job.domain.BatchJobReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class BatchJobReaderImpl implements BatchJobReader {

    private final BatchJobRepository batchJobRepository;

    @Override
    public boolean existsByStudyIdAndDayAndTime(Long studyId, DayType day, LocalTime time) {
        return batchJobRepository.existsByStudyIdAndDayAndTime(studyId, day, time);
    }

    public void validateUniqueBatchJob(Long studyId, DayType day, LocalTime time) {
        if (existsByStudyIdAndDayAndTime(studyId, day, time.withNano(0))) {
            throw new BadRequestException(ErrorCode.BATCHJOB_ALREADY_EXISTS);
        }
    }

}
