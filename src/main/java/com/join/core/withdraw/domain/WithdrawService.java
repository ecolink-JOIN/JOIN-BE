package com.join.core.withdraw.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
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

    @Transactional
    public void requestWithdraw(Long avatarId, WithdrawRequest withdrawRequest, String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);

        Avatar avatar = avatarReader.getAvatarById(avatarId);

        withdrawReader.validateWithdrawNotExists(avatar, study);

        if (!studyReader.isAvatarEnrolledInStudy(avatarId, studyToken)) {
            throw new NoPermissionException(ErrorCode.NOT_MEMBER_OF_STUDY);
        }

        withdrawStore.store(withdrawRequest, study, avatar);
    }
}