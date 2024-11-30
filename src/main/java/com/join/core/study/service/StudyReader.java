package com.join.core.study.service;

import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyReader {
    Study getStudyByToken(String studyToken);
    Study getStudyById(Long studyId);
    List<Study> getStudyOrderByPopularity(Long categoryId, StudyForm form, LocalDateTime now);
}
