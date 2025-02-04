package com.join.core.avatar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.controller.specification.PushControllerSpecification;
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
public class PushController implements PushControllerSpecification {

	private final PushService pushService;


	@PreAuthorize("isAuthenticated()")
	@PutMapping
	public ApiResponse<Void> changePushConsent(@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody AvatarCommand.ChangePushConsent command) {
		pushService.updatePushConsent(principal.getUserId(), command);
		return ApiResponse.ok();
	}

}
