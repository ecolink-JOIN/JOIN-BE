package com.join.core.rule.dto.request;

import com.join.core.rule.constant.RuleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RuleRequest {

    @Schema(description = "스터디 규칙 유형(중복 선택)", example = "FINE, EXPULSION, PHOTO_PROOF, TIMER_PROOF")
    private RuleType type;

}
