package com.join.core.enrollment.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.domain.Enrollment;
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
    public Enrollment getEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId) {
        return enrollmentRepository.findByAvatarIdAndStudyId(avatarId, studyId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_MEMBER_OF_STUDY));
    }
}
