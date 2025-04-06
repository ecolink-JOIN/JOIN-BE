package com.join.core.auth.controller.specfication;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import com.join.core.auth.domain.TermCommand;
import com.join.core.auth.domain.TermInfo;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface TermControllerSpecification {

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "약관 조회 API - 인증 필요",
		description = "약관 조회 API - 현재 유저의 동의가 필요한 약관을 조회하는 API입니다.",
		security = {@SecurityRequirement(name = "session-token")})
	ApiResponse<List<TermInfo.Main>> getRequiredConsentTerms(@AuthenticationPrincipal UserPrincipal principal);

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "유효 약관 조회 API",
		description = "유효 약관 조회 API - 현재 유효한 약관 전체를 조회하는 API입니다.",
		security = {@SecurityRequirement(name = "session-token")})
	ApiResponse<List<TermInfo.Main>> getValidTerms();

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "약관 동의 API - 인증 필요",
		description = "약관 동의 API - 받은 약관에 동의 || 비동의 이력을 남기는 API입니다.",
		security = {@SecurityRequirement(name = "session-token")})
	ApiResponse<Void> agreeTerms(@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody TermCommand.Agree command);

}
