package com.join.core.withdraw.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.study.domain.Study;
import com.join.core.withdraw.domain.WithdrawReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WithdrawReaderImpl implements WithdrawReader {

    private final WithdrawRepository withdrawRepository;

    @Override
    public void validateWithdrawNotExists(Avatar avatar, Study study) {
        if (withdrawRepository.existsByAvatarAndStudy(avatar, study)) {
            throw new BadRequestException(ErrorCode.WITHDRAW_ALREADY_EXISTS);
        }
    }

}