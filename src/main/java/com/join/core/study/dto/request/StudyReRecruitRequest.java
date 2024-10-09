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
public class StudyReRecruitRequest {

    @Schema(description = "모집 인원", example = "10")
    @NotNull
    private int capacity;

    @Schema(description = "모집 종료 날짜", example = "2024-08-31")
    @NotNull
    private LocalDate recruitEndDate;

}
