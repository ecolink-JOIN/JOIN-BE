package com.join.core.study.controller.specification;

import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.response.StudyRuleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface StudyRuleApiSpecification {

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 운영 규칙 조회",
            description = "스터디 운영 규칙을 조회합니다.")
    ApiResponse<StudyRuleResponse> getRules(String studyToken);
}
