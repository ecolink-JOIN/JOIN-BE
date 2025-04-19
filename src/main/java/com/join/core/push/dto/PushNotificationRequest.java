package com.join.core.push.dto;

import java.util.Map;

import com.join.core.push.domain.PushNotificationType;

public record PushNotificationRequest(
	Long targetAvatarId,
	PushNotificationType type,
	Map<String, String> parameters
) {

}
