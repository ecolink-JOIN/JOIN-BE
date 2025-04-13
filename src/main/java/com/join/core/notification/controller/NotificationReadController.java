package com.join.core.notification.controller;

import com.join.core.common.response.ApiResponse;
import com.join.core.notification.dto.response.NotificationResponse;
import com.join.core.notification.service.NotificationService;
import com.join.core.auth.domain.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "${swagger.tag.notification}")
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationReadController {

    private final NotificationService notificationService;

    @Operation(summary = "알림 내역 조회 - 인증 필수",
            description = "알림 내역 조회 - 사용자 기반",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ApiResponse<List<NotificationResponse>> getNotifications(@AuthenticationPrincipal  UserPrincipal principal) {
        return ApiResponse.ok(
                notificationService.getNotificationsByAvatar(principal.getAvatarId())
        );
    }
}
