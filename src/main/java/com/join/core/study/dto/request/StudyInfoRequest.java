package com.join.core.study.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudyInfoRequest {

    @Schema(description = "모집 인원", example = "10")
    @NotNull
    private int capacity;

    @Schema(description = "정기 모임 여부", example = "true")
    @NotNull
    private boolean isRegular;

    @Schema(description = "모집 종료 날짜", example = "2024-08-31")
    @NotNull
    private LocalDate recruitEndDate;

    @Schema(description = "시작 날짜", example = "2024-09-01")
    @NotNull
    private LocalDate stDate;

    @Schema(description = "종료 날짜", example = "2024-12-31")
    @NotNull
    private LocalDate endDate;

    @Schema(description = "시/도", example = "서울특별시")
    @NotNull
    private String province;

    @Schema(description = "시/군/구", example = "도봉구")
    @NotNull
    private String city;

    @Schema(description = "카테고리 이름", example = "입시")
    @NotNull
    private String categoryName;

}
