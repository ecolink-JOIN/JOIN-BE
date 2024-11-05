package com.join.core.study.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class StudyEndRequest {
    @Schema(description = "스터디 실제 종료 날짜", example = "2024-09-31")
    private LocalDate actualEndDate;

}
