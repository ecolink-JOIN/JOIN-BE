package com.join.core.notification.service;

import com.join.core.notification.domain.Notification;
import com.join.core.notification.domain.NotificationTarget;

import java.util.List;

public interface NotificationStore {
    void store(Notification notification);
    void storeTargets(List<NotificationTarget> notifications);
}
