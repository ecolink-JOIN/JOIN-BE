package com.join.core.application.controller;

import com.join.core.application.dto.request.ApplicationCreateRequest;
import com.join.core.application.dto.request.ApplicationRejectRequest;
import com.join.core.application.dto.response.ApplicationReadResponse;
import com.join.core.application.service.ApplicationDecisionService;
import com.join.core.application.service.ApplicationReadService;
import com.join.core.application.service.ApplicationService;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationDecisionService applicationDecisionService;
    private final ApplicationReadService applicationReadService;

    @Tag(name = "${swagger.tag.application}")
    @Operation(summary = "스터디 지원 - 인증 필수",
            description = "스터디 지원 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> apply(@AuthenticationPrincipal UserPrincipal principal,
                                   @Valid @RequestBody ApplicationCreateRequest request) {
        Long avatarId = principal.getAvatarId();
        applicationService.apply(avatarId, request);
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.application}")
    @Operation(summary = "스터디 지원 승인 - 인증 필수",
            description = "스터디 지원 승인 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{applicationId}/accept")
    public ApiResponse<Void> acceptApplication(@PathVariable Long applicationId,
                                               @AuthenticationPrincipal UserPrincipal principal) {
        applicationDecisionService.acceptApplication(applicationId, principal.getAvatarId());
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.application}")
    @Operation(summary = "스터디 지원 반려 - 인증 필수",
            description = "스터디 지원 반려 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{applicationId}/reject")
    public ApiResponse<Void> rejectApplication(@PathVariable Long applicationId,
                                               @AuthenticationPrincipal UserPrincipal principal,
                                               @RequestBody ApplicationRejectRequest rejectRequest) {
        applicationDecisionService.rejectApplication(applicationId, principal.getAvatarId(),
                rejectRequest.getRejectReason(), rejectRequest.getOtherReason());
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.application}")
    @Operation(summary = "스터디 지원자 조회 - 인증 필수",
            description = "스터디 지원자 조회 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{studyToken}")
    public ApiResponse<List<ApplicationReadResponse>> getApplications(@AuthenticationPrincipal UserPrincipal principal,
                                                                      @PathVariable String studyToken) {
        List<ApplicationReadResponse> responses = applicationReadService.getApplicationDetails(studyToken, principal.getAvatarId());
        return ApiResponse.ok(responses);
    }

}
