package com.join.core.notification.repository;

import com.join.core.notification.domain.Notification;
import com.join.core.notification.service.NotificationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationStoreImpl implements NotificationStore {

    private final NotificationRepository notificationRepository;

    @Override
    public void store(Notification notification) {
        notificationRepository.save(notification);
    }

}
