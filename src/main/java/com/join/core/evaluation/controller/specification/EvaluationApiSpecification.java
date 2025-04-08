package com.join.core.evaluation.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.evaluation.dto.request.EvaluationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface EvaluationApiSpecification {

    @Tag(name = "${swagger.tag.evaluation}")
    @Operation(summary = "스터디원 평가 API - 인증 필수",
            description = "스터디원 평가 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> addEvaluation(
            @AuthenticationPrincipal UserPrincipal principal,
            EvaluationRequest evaluationRequest
    );

}
