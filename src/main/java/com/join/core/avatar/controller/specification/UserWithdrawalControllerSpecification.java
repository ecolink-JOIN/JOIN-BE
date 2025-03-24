package com.join.core.avatar.controller.specification;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.dto.response.WithdrawalAvailabilityResponse;
import com.join.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface UserWithdrawalControllerSpecification {

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "회원탈퇴 가능 여부 확인 API - 인증 필요",
		description = "회원탈퇴 가능 여부 확인 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	ApiResponse<WithdrawalAvailabilityResponse> canWithdrawal(@AuthenticationPrincipal UserPrincipal principal);

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "회원탈퇴 API - 인증 필요",
		description = "회원탈퇴 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	ApiResponse<WithdrawalAvailabilityResponse> withdraw(@AuthenticationPrincipal UserPrincipal principal);

}
