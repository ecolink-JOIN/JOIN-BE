package com.join.core.notification.dto.response;

import com.join.core.notification.constant.NotificationType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NotificationResponse(
        Long notificationId,
        String content,
        NotificationType type,
        LocalDateTime createdAt,
        boolean isRead
) {}
