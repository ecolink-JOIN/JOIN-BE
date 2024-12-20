package com.join.core.notification.service;

import com.join.core.notification.domain.Notification;

public interface NotificationStore {
    void store(Notification notification);

}
