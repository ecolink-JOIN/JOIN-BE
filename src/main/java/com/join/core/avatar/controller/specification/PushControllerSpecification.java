package com.join.core.avatar.controller.specification;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.domain.AvatarCommand;
import com.join.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface PushControllerSpecification {

	@Tag(name = "${swagger.tag.sign-up}")
	@Tag(name = "${swagger.tag.user}")
	@Operation(summary = "푸시 알림 동의 API - 인증 필요",
		description = "푸시 알림 동의 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	ApiResponse<Void> changePushConsent(@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody AvatarCommand.ChangePushConsent command);

}
