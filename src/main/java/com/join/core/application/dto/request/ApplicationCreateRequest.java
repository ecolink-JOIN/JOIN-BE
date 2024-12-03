package com.join.core.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ApplicationCreateRequest {

    @Schema(description = "지원 소개", example = "저는 이 스터디에 열정적으로 참여하고 싶습니다.")
    @NotBlank
    private String introduction;

    @Schema(description = "지원 날짜", example = "2024-09-01")
    @NotNull
    private LocalDate appDate;

    @Schema(description = "스터디 토큰", example = "std_abc123")
    @NotBlank
    private String studyToken;

}
