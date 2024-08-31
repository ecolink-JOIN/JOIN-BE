package com.join.core.auth.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ApiResponse<List<TermInfo.Main>> get(@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(termService.getRequiredConsentTerms(principal.getUserId()));
	}

}
