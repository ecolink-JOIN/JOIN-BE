package com.join.core.auth.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.join.core.auth.controller.specfication.TermControllerSpecification;
import com.join.core.auth.domain.TermCommand;
import com.join.core.auth.domain.TermInfo;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.auth.service.TermService;
import com.join.core.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")
public class TermController implements TermControllerSpecification {

	private final TermService termService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/terms")
	public ApiResponse<List<TermInfo.Main>> getRequiredConsentTerms(@AuthenticationPrincipal UserPrincipal principal) {
		return ApiResponse.ok(termService.getRequiredConsentTerms(principal.getUserId()));
	}

	@PostMapping("/terms/all")
	public ApiResponse<List<TermInfo.Main>> getValidTerms() {
		return ApiResponse.ok(termService.getValidTerms());
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/terms/agree")
	public ApiResponse<Void> agreeTerms(@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody TermCommand.Agree command) {
		termService.agreeTerm(principal.getUserId(), command);
		return ApiResponse.ok();
	}

}
