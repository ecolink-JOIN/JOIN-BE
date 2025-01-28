package com.join.core.evaluation.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.evaluation.controller.specification.EvaluationApiSpecification;
import com.join.core.evaluation.domain.EvaluationService;
import com.join.core.evaluation.dto.request.EvaluationRequest;
import com.join.core.common.response.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/evaluation")
public class EvaluationController implements EvaluationApiSpecification {

    private final EvaluationService evaluationService;

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> addEvaluation(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody EvaluationRequest evaluationRequest
    ) {
        evaluationService.evaluate(evaluationRequest, principal.getAvatarId());
        return ApiResponse.ok();
    }

}