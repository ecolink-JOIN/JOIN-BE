package com.join.core.withdraw.repository;

import com.join.core.withdraw.domain.Withdraw;
import com.join.core.withdraw.domain.WithdrawStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WithdrawStoreImpl implements WithdrawStore {

    private final WithdrawRepository withdrawRepository;

    @Override
    public void store(Withdraw withdraw) {
        withdrawRepository.save(withdraw);
    }
}
