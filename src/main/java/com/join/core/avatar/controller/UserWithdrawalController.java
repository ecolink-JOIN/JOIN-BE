package com.join.core.avatar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.controller.specification.UserWithdrawalControllerSpecification;
import com.join.core.avatar.dto.response.WithdrawalAvailabilityResponse;
import com.join.core.avatar.service.UserWithdrawalService;
import com.join.core.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/avatars/withdraw")
public class UserWithdrawalController implements UserWithdrawalControllerSpecification {

	private final UserWithdrawalService userWithdrawalService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/check")
	public ApiResponse<WithdrawalAvailabilityResponse> canWithdrawal(
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(userWithdrawalService.canWithdrawal(principal.getAvatarId()));
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public ApiResponse<WithdrawalAvailabilityResponse> withdraw(
		@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(userWithdrawalService.withdraw(principal.getAvatarId()));
	}

}
