package com.join.core.rule.dto.response;

import com.join.core.rule.constant.RuleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RuleResponse {
    @Schema(description = "스터디 규칙 유형", example = "벌금 있음")
    private RuleType type;

}
