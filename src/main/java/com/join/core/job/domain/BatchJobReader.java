package com.join.core.job.domain;

import com.join.core.common.constant.DayType;

import java.time.LocalTime;
import java.util.List;

public interface BatchJobReader {
    boolean existsByStudyStudyTokenAndDayAndTime(String studyToken, DayType day, LocalTime time);
    void validateUniqueBatchJob(String studyToken, DayType day, LocalTime time);
    BatchJob getBatchJobById(Long batchJobId);
    List<BatchJob> getBatchJobsByStudyToken(String studyToken);

}
