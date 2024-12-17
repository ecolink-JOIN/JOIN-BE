package com.join.core.application.dto.request;

import com.join.core.application.constant.ApplicationRejectReason;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationRejectRequest {

    @Schema(description = "거절 사유", example = "출석률")
    @NotBlank
    private ApplicationRejectReason rejectReason;

    @Schema(description = "거절 기타 사유", example = "스터디 성격과 맞지 않은 것 같습니다.")
    private String otherReason;

}