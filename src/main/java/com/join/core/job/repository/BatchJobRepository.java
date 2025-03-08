package com.join.core.job.repository;

import com.join.core.common.constant.DayType;
import com.join.core.job.domain.BatchJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface BatchJobRepository extends JpaRepository<BatchJob, Long> {
    boolean existsByStudyStudyTokenAndDayAndTime(String studyToken, DayType day, LocalTime time);

}