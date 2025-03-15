package com.join.core.notification.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.notification.dto.request.NotificationRequest;
import com.join.core.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/notice")
public class NotificationController {

    private final NotificationService notificationService;

    @Tag(name = "${swagger.tag.notification}")
    @Operation(summary = "스터디 공지 생성 - 인증 필수",
            description = "스터디 공지 생성 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> createNotice(@AuthenticationPrincipal UserPrincipal principal,
                                          @PathVariable String studyToken,
                                          @RequestBody NotificationRequest request) {
        notificationService.postNotice(studyToken, request, principal.getAvatarId());
        return ApiResponse.ok();
    }

}
