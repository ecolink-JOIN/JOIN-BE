package com.join.core.withdraw.domain;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.withdraw.dto.response.WithdrawResponse;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class WithdrawReadService {

    private final WithdrawReader withdrawReader;
    private final StudyReader studyReader;

    public List<WithdrawResponse> getWithdrawRequests(String studyToken, Long avatarId) {
        Study study = studyReader.getStudyByToken(studyToken);
        List<Withdraw> withdrawRequests = withdrawReader.findWithdrawRequests(study);

        if (!study.getWriter().getId().equals(avatarId)) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        return withdrawRequests.stream()
                .map(withdraw -> WithdrawResponse.from(withdraw, withdraw.getAvatar()))
                .collect(Collectors.toList());
    }

}