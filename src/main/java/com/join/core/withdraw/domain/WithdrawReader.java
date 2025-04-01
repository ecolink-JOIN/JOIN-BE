package com.join.core.withdraw.domain;

import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;

import java.util.List;

public interface WithdrawReader {
    void validateWithdrawNotExists(Avatar avatar, Study study);
    List<Withdraw> findWithdrawRequests(Study study);
}
