package com.join.core.study.repository;

import com.join.core.study.domain.Study;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface StudyQueryRepository {

    Page<Study> getStudiesOrderByPopularity(EssentialStudyCondition condition, LocalDateTime now, Pageable pageable);
}
