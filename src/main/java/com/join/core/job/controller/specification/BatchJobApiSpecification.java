package com.join.core.job.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.job.dto.request.BatchJobRequest;
import com.join.core.job.dto.request.BatchJobUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BatchJobApiSpecification {

    @Tag(name = "${swagger.tag.batch-job}")
    @Operation(summary = "자동 알림 등록 API - 인증 필수",
            description = "자동 알림 등록 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> addBatchJob(
            @AuthenticationPrincipal UserPrincipal principal,
            BatchJobRequest batchJobRequest
    );

    @Tag(name = "${swagger.tag.batch-job}")
    @Operation(summary = "자동 알림 변경 API - 인증 필수",
            description = "자동 알림 변경 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> updateBatchJob(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long batchJobId,
            @RequestBody BatchJobUpdateRequest batchJobUpdateRequest
    );

}