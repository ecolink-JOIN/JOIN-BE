package com.join.core.withdraw.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.withdraw.dto.request.WithdrawRequest;
import com.join.core.withdraw.dto.response.WithdrawResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface WithdrawApiSpecification {
    @Tag(name = "${swagger.tag.withdraw}")
    @Operation(summary = "스터디 탈퇴 API - 인증 필수",
            description = "스터디 탈퇴 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> createWithdraw(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            WithdrawRequest withdrawRequest
    );

    @Tag(name = "${swagger.tag.withdraw}")
    @Operation(summary = "스터디 탈퇴 요청 조회 API - 인증 필수",
            description = "스터디 탈퇴 요청 조회 API - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<List<WithdrawResponse>> getWithdrawRequests(
            @AuthenticationPrincipal UserPrincipal principal,

            @PathVariable String studyToken
    );
}