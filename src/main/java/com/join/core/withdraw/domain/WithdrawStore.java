package com.join.core.withdraw.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;
import com.join.core.withdraw.dto.request.WithdrawRequest;

public interface WithdrawStore {
    void store(WithdrawRequest withdrawRequest, Study study, Avatar avatar);
}