package com.join.core.withdraw.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.proof.service.ProofReader;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import com.join.core.enrollment.domain.Enrollment;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.withdraw.constant.WithdrawType;
import com.join.core.withdraw.dto.request.WithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WithdrawService {

    private final WithdrawReader withdrawReader;
    private final WithdrawStore withdrawStore;
    private final StudyReader studyReader;
    private final AvatarReader avatarReader;
    private final EnrollmentReader enrollmentReader;
    private final ProofReader proofReader;

    @Transactional
    public void requestWithdraw(Long avatarId, WithdrawRequest withdrawRequest, String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);
        Avatar avatar = avatarReader.getAvatarById(avatarId);

        withdrawReader.validateWithdrawNotExists(avatar, study);

        if (!studyReader.isAvatarEnrolledInStudy(avatarId, studyToken)) {
            throw new NoPermissionException(ErrorCode.NOT_MEMBER_OF_STUDY);
        }

        Enrollment enrollment = enrollmentReader.getEnrollmentByAvatarIdAndStudyId(avatarId, study.getId());
        if (withdrawRequest.getWithdrawType() == WithdrawType.SELF_WITHDRAW) {
            enrollment.withdraw();
            withdrawStore.store(new Withdraw(study, avatar, withdrawRequest.getWithdrawType(), withdrawRequest.getReason()));
        }
    }

    @Transactional
    public void approveWithdraw(Long withdrawId, Long avatarId, String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);
        Withdraw withdraw = withdrawReader.findByIdAndStudy(withdrawId, study);

        if (!withdraw.getAvatar().getId().equals(avatarId)) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        if (!proofReader.isFullyApproved(avatarId, study.getId())) {
            throw new BadRequestException(ErrorCode.PROOF_NOT_APPROVED);
        }

        withdraw.approveWithdraw();
        withdrawStore.store(withdraw);

    }

}
