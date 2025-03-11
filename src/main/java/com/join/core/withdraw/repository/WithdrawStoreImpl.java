package com.join.core.withdraw.repository;

import com.join.core.withdraw.domain.Withdraw;
import com.join.core.withdraw.domain.WithdrawStore;
import com.join.core.withdraw.dto.request.WithdrawRequest;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WithdrawStoreImpl implements WithdrawStore {

    private final WithdrawRepository withdrawRepository;

    @Override
    public void store(WithdrawRequest withdrawRequest, Study study, Avatar avatar) {

        Withdraw withdraw = withdrawRequest.toEntity(study, avatar);

        withdrawRepository.save(withdraw);
    }
}
