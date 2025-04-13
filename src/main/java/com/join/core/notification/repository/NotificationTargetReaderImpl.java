package com.join.core.notification.repository;

import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.notification.domain.Notification;
import com.join.core.notification.domain.NotificationTarget;
import com.join.core.notification.dto.response.NotificationResponse;
import com.join.core.notification.service.NotificationReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NotificationTargetReaderImpl implements NotificationReader {

    private final NotificationTargetRepository notificationTargetRepository;

    @Override
    public List<NotificationResponse> readAllByAvatar(Long avatarId) {
        List<NotificationTarget> notificationTargets = notificationTargetRepository.findWithNotificationByTargetId(avatarId);

        if (notificationTargets.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.NOTIFICATION_NOT_FOUND);
        }

        return notificationTargets.stream()
                .map(nt -> {
                    Notification n = nt.getNotification();
                    return NotificationResponse.builder()
                            .notificationId(n.getId())
                            .title(n.getTitle())
                            .content(n.getContent())
                            .type(n.getNotificationType())
                            .createdAt(n.getCreatedDate())
                            .isRead(nt.isRead())
                            .build();
                })
                .toList();
    }
}
