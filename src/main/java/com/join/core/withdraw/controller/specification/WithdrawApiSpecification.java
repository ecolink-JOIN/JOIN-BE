package com.join.core.withdraw.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.withdraw.dto.request.WithdrawRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface WithdrawApiSpecification {
    @Tag(name = "${swagger.tag.withdraw}")
    @Operation(summary = "탈퇴 API - 인증 필수",
            description = "탈퇴 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> createWithdraw(
            @AuthenticationPrincipal UserPrincipal principal,
            WithdrawRequest withdrawRequest
    );

}