package com.join.core.enrollment.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.service.EnrollmentReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EnrollmentReaderImpl implements EnrollmentReader {

    private final EnrollmentQueryRepository enrollmentQueryRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public double getAverageByStudyId(Long studyId) {
        return enrollmentQueryRepository.getMemberAverageByStudyId(studyId);
    }

    @Override
    public Avatar getLeaderByStudyId(Long studyId) {
        return enrollmentQueryRepository.getLeaderByStudyId(studyId);
    }

    @Override
    public boolean existEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId) {
        return enrollmentRepository.existsByAvatarIdAndStudyIdAndStatus(avatarId, studyId, EnrollmentStatus.JOINED);
    }

    @Override
    public void validateEnrollment(Long avatarId, Long studyId) {
        boolean exists = enrollmentRepository.existsByAvatarIdAndStudyIdAndStatusNot(avatarId, studyId, EnrollmentStatus.PENDING);
        if (!exists) {
            throw new InvalidParamException(ErrorCode.INVALID_PARAMETER, "스터디 참여자가 아닙니다.");
        }
    }

}