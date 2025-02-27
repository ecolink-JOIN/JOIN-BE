package com.join.core.job.domain;

import com.join.core.common.constant.DayType;

import java.time.LocalTime;

public interface BatchJobReader {
    boolean existsByStudyStudyTokenAndDayAndTime(String studyToken, DayType day, LocalTime time);
    void validateUniqueBatchJob(String studyToken, DayType day, LocalTime time);

}
