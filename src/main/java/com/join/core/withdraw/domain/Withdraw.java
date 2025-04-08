package com.join.core.withdraw.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.study.domain.Study;
import com.join.core.withdraw.constant.WithdrawType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.join.core.withdraw.constant.WithdrawStatus;

@Getter
@NoArgsConstructor
@Entity
public class Withdraw extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Study study;

    @ManyToOne
    private Avatar avatar;

    @Enumerated(EnumType.STRING)
    private WithdrawType withdrawType;

    @Enumerated(EnumType.STRING)
    private WithdrawStatus status;

    private String reason;

    public Withdraw(Study study, Avatar avatar, WithdrawType withdrawType, String reason) {
        this.study = study;
        this.avatar = avatar;
        this.withdrawType = withdrawType;
        this.reason = reason;

        if (this.withdrawType == WithdrawType.SELF_WITHDRAW) {
            this.status = WithdrawStatus.APPROVED;
        } else {
            this.status = WithdrawStatus.PENDING;
        }
    }

    public void approveWithdraw() {
        this.status = WithdrawStatus.APPROVED;
    }

}