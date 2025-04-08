package com.join.core.study.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record FineReasonAmountsDto(
            @Schema(description = "지각 벌금 금액", example = "1000")
            Integer tardiness,
            @Schema(description = "결석 벌금 금액", example = "3000")
            Integer absence,
            @Schema(description = "미인증 벌금 금액", example = "3000")
            Integer nonProof
    ) {
    }