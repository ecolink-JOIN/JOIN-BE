package com.join.core.proof.constant;

import lombok.Getter;

@Getter
public enum ProofStatus {

    PENDING(0.0),
    APPROVED(1.0),
    REJECTED(0.0);

    private final double reflectionRate;

    ProofStatus(double reflectionRate) {
        this.reflectionRate = reflectionRate;
    }

    public boolean isPending() {
        return this == PENDING;
    }
}
