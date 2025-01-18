package com.join.core.proof.dto.response;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.ServerStatusException;
import com.join.core.proof.constant.ProofStatus;

import java.util.Arrays;

public enum ProofStatusResponse {

    NONE,
    PENDING,
    APPROVED,
    REJECTED;

    public static ProofStatusResponse getProofStatusRequest(ProofStatus proofStatus) {
        return Arrays.stream(ProofStatusResponse.values())
                .filter(p -> p.toString().equals(proofStatus.toString()))
                .findAny()
                .orElseThrow(() -> new ServerStatusException(ErrorCode.INVALID_PROOF_STATUS));
    }
}
