package com.join.core.report.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.join.core.report.constant.ReportType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReportRequest {

    @NotNull
    @Schema(description = "신고 유형", example = "STUDY_CONTENT")
    private ReportType reportType;

    @NotNull
    @Schema(description = "스터디 토큰", example = "std_abc")
    private String studyToken;

    @NotBlank
    @Size(min = 10, max = 300)
    @Schema(description = "신고 사유", example = "스터디 모집 게시글이 아니에요.")
    private String reason;

}