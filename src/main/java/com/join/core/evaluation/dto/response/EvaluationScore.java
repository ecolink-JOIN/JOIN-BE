package com.join.core.evaluation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EvaluationScore {

    @Schema(description = "스터디장 평점", example = "4.7")
    private final double leaderScore;

    @Schema(description = "스터디원 평점", example = "4.9")
    private final double memberScore;

}