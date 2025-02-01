package com.join.core.study.repository;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.common.exception.impl.InvalidStateException;
import com.join.core.enrollment.constant.StudyRole;
import com.join.core.study.domain.Study;
import com.join.core.study.repository.condition.CustomStudyCondition;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.join.core.study.constant.StudyStatus;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StudyReaderImpl implements StudyReader {

    private final StudyRepository studyRepository;
    private final StudyQueryRepository studyQueryRepository;

    @Override
    public Study getStudyByToken(String studyToken) {
        return studyRepository.findByStudyToken(studyToken)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STUDY_NOT_FOUND));
    }

    @Override
    public Study getStudyById(Long studyId) {
        return studyRepository.findById(studyId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STUDY_NOT_FOUND));
    }

    @Override
    public Page<Study> getStudyOrderByPopularity(EssentialStudyCondition condition, LocalDateTime now, Pageable pageable) {
        return studyQueryRepository.getStudiesOrderByPopularity(
                condition,
                now,
                pageable
        );
    }

    @Override
    public List<Study> getStudiesOrderByRecommendations(EssentialStudyCondition condition, CustomStudyCondition customStudyCondition) {
        return studyQueryRepository.getStudiesOrderByRecommendations(condition, customStudyCondition);
    }

    @Override
    public List<Study> getStudiesByLeaderAvatarId(Long avatarId) {
        return studyQueryRepository.findAllByAvatarIdAndRole(avatarId, StudyRole.LEADER);
    }

    @Override
    public Page<Study> getStudiesByTitleContaining(String keyword, Pageable pageable) {
        return studyRepository.findAllByTitleContaining(keyword, pageable);
    }

    @Override
    public Study validateStudyCompletion(Long studyId) {
        return studyRepository.findByIdAndStatus(studyId, StudyStatus.COMPLETED)
                .orElseThrow(() -> new InvalidStateException(ErrorCode.EVALUATION_PERIOD_INVALID));
    }

    @Override
    public boolean existsByEnrollmentsAvatarToken(String subjectToken, String targetToken) {
        return studyQueryRepository.existsByEnrollmentsAvatarToken(subjectToken, targetToken);
    }
}
