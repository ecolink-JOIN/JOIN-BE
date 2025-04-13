package com.join.core.notification.service;

import com.join.core.notification.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationReader {
    List<NotificationResponse> readAllByAvatar(Long avatarId);
}