package com.join.core.notification.repository;

import com.join.core.notification.domain.Notification;
import com.join.core.notification.domain.NotificationTarget;
import com.join.core.notification.service.NotificationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NotificationStoreImpl implements NotificationStore {

    private final NotificationRepository notificationRepository;
    private final NotificationTargetRepository notificationTargetRepository;

    @Override
    public void store(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void storeTargets(List<NotificationTarget> notifications) {
        notificationTargetRepository.saveAll(notifications);
    }

}
