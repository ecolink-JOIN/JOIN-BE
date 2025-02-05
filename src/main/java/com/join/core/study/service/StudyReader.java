package com.join.core.study.service;

import com.join.core.study.domain.Study;
import com.join.core.study.repository.condition.CustomStudyCondition;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyReader {
    Study getStudyByToken(String studyToken);
    Study getStudyById(Long studyId);
    Page<Study> getStudyOrderByPopularity(EssentialStudyCondition condition, LocalDateTime now, Pageable pageable);
    List<Study> getStudiesOrderByRecommendations(EssentialStudyCondition condition, CustomStudyCondition customStudyCondition);
    List<Study> getStudiesByLeaderAvatarId(Long avatarId);
    List<Study> getJoinedStudiesByAvatarId(Long avatarId);
    Page<Study> getStudiesByTitleContaining(String keyword, Pageable pageable);
    Study validateStudyCompletion(Long studyId);
    boolean existsByEnrollmentsAvatarToken(String subjectToken, String targetToken);
}
