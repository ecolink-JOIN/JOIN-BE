package com.join.core.avatar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.domain.AvatarCommand;
import com.join.core.avatar.service.PushService;
import com.join.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/avatars/push")
public class PushController {

	private final PushService pushService;

	@Tag(name = "${swagger.tag.sign-up}")
	@Tag(name = "${swagger.tag.user}")
	@Operation(summary = "푸시 알림 동의 API - 인증 필요",
		description = "푸시 알림 동의 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("isAuthenticated()")
	@PutMapping
	public ApiResponse<Void> changePushConsent(@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody AvatarCommand.ChangePushConsent command) {
		pushService.updatePushConsent(principal.getUserId(), command);
		return ApiResponse.ok();
	}

}
