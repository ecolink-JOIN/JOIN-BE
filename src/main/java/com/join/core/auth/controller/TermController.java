package com.join.core.auth.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.join.core.auth.domain.TermCommand;
import com.join.core.auth.domain.TermInfo;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.auth.service.TermService;
import com.join.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class TermController {

	private final TermService termService;

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "약관 조회 API - 인증 필요",
		description = "약관 조회 API - 현재 유저의 동의가 필요한 약관을 조회하는 API입니다.",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/terms")
	public ApiResponse<List<TermInfo.Main>> getRequiredConsentTerms(@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(termService.getRequiredConsentTerms(principal.getUserId()));
	}

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "약관 동의 API - 인증 필요",
		description = "약관 동의 API - 받은 약관에 동의 || 비동의 이력을 남기는 API입니다.",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/terms/agree")
	public ApiResponse<Void> agreeTerms(@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody TermCommand.Agree command) {
		termService.agreeTerm(principal.getUserId(), command);
		return ApiResponse.ok();
	}

}
