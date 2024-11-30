package com.join.core.study.repository;

import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyQueryRepository {

    List<Study> getStudiesOrderByPopularity(Long categoryId, StudyForm form, LocalDateTime now);
}
