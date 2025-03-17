package com.join.core.study.service;

import com.join.core.study.domain.Study;
import com.join.core.study.repository.condition.CustomStudyCondition;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.repository.condition.SearchCondition;
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
    List<Study> getInterestStudiesByAvatarId(Long avatarId);
    Page<Study> getStudiesByTitleAndConditions(SearchCondition condition, Pageable pageable);
    Study validateStudyCompletion(String studyToken);
    boolean existsByEnrollmentsAvatarToken(String subjectToken, String targetToken);
    List<Study> getActiveStudyByTokens(Long subjectId, Long targetId);
    List<Study> getStudiesByAvatarId(Long avatarId);
}
