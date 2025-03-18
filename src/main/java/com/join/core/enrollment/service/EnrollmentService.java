package com.join.core.enrollment.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.enrollment.domain.Enrollment;
import com.join.core.enrollment.dto.request.EnrollmentCreateRequest;
import com.join.core.enrollment.repository.EnrollmentRepository;
import com.join.core.enrollment.service.dto.DelegateLeaderParams;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final AvatarReader avatarReader;
    private final StudyReader studyReader;
    private final EnrollmentReader enrollmentReader;

    public void createEnrollment(EnrollmentCreateRequest request, Study study, Avatar avatar) {
        Enrollment enrollment = new Enrollment(
                study,
                avatar,
                request.getStatus(),
                request.getEnrolledDate(),
                null,
                null,
                request.getRole()
        );
        enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void delegateStudyLeader(DelegateLeaderParams params) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(params.avatarToken());
        Study study = studyReader.getStudyByToken(params.studyToken());
        validateLeaderPermission(avatar, study.getId());

        Avatar target = avatarReader.getAvatarByAvatarToken(params.targetToken());
        changeLeader(target, avatar, study.getId());
    }

    private void validateLeaderPermission(Avatar avatar, Long studyId) {
        Avatar leader = enrollmentReader.getLeaderByStudyId(studyId);
        if (!leader.isSameAvatar(avatar.getId())) {
            throw new NoPermissionException(ErrorCode.LEADER_ONLY_ACCESS);
        }
    }

    private void changeLeader(Avatar newLeader, Avatar oldLeader, Long studyId) {
        Enrollment oldLeaderEnrollment = enrollmentReader.getEnrollmentByAvatarIdAndStudyId(oldLeader.getId(), studyId);
        Enrollment newLeaderEnrollment = enrollmentReader.getEnrollmentByAvatarIdAndStudyId(newLeader.getId(), studyId);

        oldLeaderEnrollment.delegateLeader();
        newLeaderEnrollment.appointLeader();
    }
}
