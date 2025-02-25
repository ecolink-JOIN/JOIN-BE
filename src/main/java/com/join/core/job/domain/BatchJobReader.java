package com.join.core.job.domain;

import com.join.core.common.constant.DayType;

import java.time.LocalTime;

public interface BatchJobReader {
    boolean existsByStudyIdAndDayAndTime(Long studyId, DayType day, LocalTime time);
    void validateUniqueBatchJob(Long studyId, DayType day, LocalTime time);

}
