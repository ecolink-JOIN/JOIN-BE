package com.join.core.application.controller;

import com.join.core.application.dto.request.ApplicationCreateRequest;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Tag(name = "${swagger.tag.application}")
    @Operation(summary = "스터디 지원 - 인증 필수",
            description = "스터디 지원 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/apply")
    public ApiResponse<Void> apply(@AuthenticationPrincipal UserPrincipal principal,
                                   @Valid @RequestBody ApplicationCreateRequest request) {
        String avatarToken = principal.getAvatarToken();
        applicationService.apply(avatarToken, request);
        return ApiResponse.ok();
    }

}
