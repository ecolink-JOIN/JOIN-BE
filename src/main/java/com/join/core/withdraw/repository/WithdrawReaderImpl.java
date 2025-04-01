package com.join.core.withdraw.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.study.domain.Study;
import com.join.core.withdraw.constant.WithdrawStatus;
import com.join.core.withdraw.constant.WithdrawType;
import com.join.core.withdraw.domain.Withdraw;
import com.join.core.withdraw.domain.WithdrawReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class WithdrawReaderImpl implements WithdrawReader {

    private final WithdrawRepository withdrawRepository;

    @Override
    public void validateWithdrawNotExists(Avatar avatar, Study study) {
        if (withdrawRepository.existsByAvatarAndStudy(avatar, study)) {
            throw new BadRequestException(ErrorCode.STUDY_WITHDRAW_ALREADY_EXISTS);
        }
    }

    @Override
    public Withdraw findByIdAndStudy(Long withdrawId, Study study) {
        return withdrawRepository.findByIdAndStudy(withdrawId, study)
                .filter(withdraw -> withdraw.getStatus() == WithdrawStatus.PENDING && withdraw.getWithdrawType() == WithdrawType.APPROVAL_REQUIRED)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STUDY_WITHDRAW_NOT_FOUND));

    @Override
    public List<Withdraw> findWithdrawRequests(Study study) {
        return withdrawRepository.findByStatusAndWithdrawType(WithdrawStatus.PENDING, WithdrawType.APPROVAL_REQUIRED);

    }

}