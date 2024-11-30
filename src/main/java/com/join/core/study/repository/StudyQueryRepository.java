package com.join.core.study.repository;

import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface StudyQueryRepository {

    Page<Study> getStudiesOrderByPopularity(Long categoryId, StudyForm form, LocalDateTime now, Pageable pageable);
}
