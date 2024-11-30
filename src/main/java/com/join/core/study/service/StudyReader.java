package com.join.core.study.service;

import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface StudyReader {
    Study getStudyByToken(String studyToken);
    Study getStudyById(Long studyId);
    Page<Study> getStudyOrderByPopularity(Long categoryId, StudyForm form, LocalDateTime now, Pageable pageable);
}
