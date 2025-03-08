package com.join.core.report.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.report.dto.request.ReportRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface ReportApiSpecification {
    @Tag(name = "${swagger.tag.report}")
    @Operation(summary = "신고 API - 인증 필수",
            description = "신고 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> createReport(
            @AuthenticationPrincipal UserPrincipal principal,
            ReportRequest reportRequest
    );

}