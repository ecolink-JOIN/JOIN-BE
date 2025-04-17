package com.join.core.push.service;

import org.springframework.stereotype.Component;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.join.core.push.domain.SendFailureException;
import com.join.core.push.domain.Vendor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FcmPushNotificationSender implements PushNotificationSender {

	private final FirebaseMessaging firebaseMessaging;

	@Override
	public void send(String token, String title, String body) throws SendFailureException {
		Message message = Message.builder()
			.setToken(token)
			.putData("title", title)
			.putData("body", body)
			.build();

		try {
			firebaseMessaging.send(message);
		} catch (FirebaseMessagingException e) {
			throw new SendFailureException(Vendor.FCM, title, body, e.getMessage());
		}

	}

}
