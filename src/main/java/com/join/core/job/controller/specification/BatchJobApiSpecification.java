package com.join.core.job.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.job.dto.request.BatchJobRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface BatchJobApiSpecification {

    @Tag(name = "${swagger.tag.batch-job}")
    @Operation(summary = "자동 알림 등록 API - 인증 필수",
            description = "자동 알림 등록 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> addBatchJob(
            @AuthenticationPrincipal UserPrincipal principal,
            BatchJobRequest batchJobRequest
    );

}