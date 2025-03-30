package com.join.core.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AvatarPerformance {

    @Schema(description = "평균 출석율", example = "4.3")
    private double attendanceRate;

    @Schema(description = "평균 인증율", example = "3.2")
    private double proofRate;

    @Schema(description = "평점", example = "5.0")
    private double rating;
}