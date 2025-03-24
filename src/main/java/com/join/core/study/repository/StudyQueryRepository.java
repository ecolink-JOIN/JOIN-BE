package com.join.core.study.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.join.core.enrollment.constant.StudyRole;
import com.join.core.study.domain.Study;
import com.join.core.study.repository.condition.CustomStudyCondition;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.repository.condition.SearchCondition;

import java.time.LocalDateTime;
import java.util.List;

public interface StudyQueryRepository {

    Page<Study> getStudiesOrderByPopularity(EssentialStudyCondition condition, LocalDateTime now, Pageable pageable);
    List<Study> getStudiesOrderByRecommendations(EssentialStudyCondition essentialStudyCondition, CustomStudyCondition customStudyCondition);
    boolean existsByEnrollmentsAvatarToken(String subjectToken, String targetToken);
    List<Study> findAllByAvatarIdAndRole(Long avatarId, StudyRole status);
    List<Study> findByAvatarId(Long avatarId);
    List<Study> findJoinedStudyByAvatarId(Long avatarId);
    List<Study> findBookmarkStudyByAvatarId(Long avatarId);
    boolean existsByEnrollmentAvatarIdAndStudyToken(Long avatarId, String studyToken);
    Page<Study> searchByConditions(SearchCondition condition, Pageable pageable);
}
