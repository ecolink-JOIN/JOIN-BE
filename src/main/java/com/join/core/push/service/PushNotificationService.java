package com.join.core.push.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.User;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.push.domain.PushNotificationType;
import com.join.core.push.domain.SendFailureException;
import com.join.core.push.dto.PushNotificationRequest;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PushNotificationService {

	private final AvatarReader avatarReader;
	private final PushNotificationSender pushNotificationSender;
	private final PushNotificationFailureStore pushNotificationFailureStore;

	@Transactional
	public void sendPushNotification(PushNotificationRequest request) {
		User user = avatarReader.getById(request.targetAvatarId()).getUser();
		PushNotificationType type = request.type();
		try {
			pushNotificationSender.send(user.getFcmToken(), type.getTitle(), type.apply(request.parameters()));
		} catch (SendFailureException e) {
			pushNotificationFailureStore.store(user.getId(), e);
		}
	}

}
