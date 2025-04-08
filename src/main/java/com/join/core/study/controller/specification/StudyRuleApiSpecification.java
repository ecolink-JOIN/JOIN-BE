package com.join.core.study.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.request.UpdateStudyRuleRequest;
import com.join.core.study.dto.response.StudyRuleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudyRuleApiSpecification {

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 운영 규칙 조회",
            description = "스터디 운영 규칙을 조회합니다.")
    ApiResponse<StudyRuleResponse> getRules(String studyToken);

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 운영 규칙 수정 - 인증 필수",
            description = "스터디 운영 규칙을 수정합니다.",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> updateRules(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("studyToken") String studyToken,
            @RequestBody UpdateStudyRuleRequest updateStudyRuleRequest);
}
