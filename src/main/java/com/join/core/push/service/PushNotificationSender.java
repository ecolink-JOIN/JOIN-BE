package com.join.core.push.service;

import com.join.core.push.domain.SendFailureException;

public interface PushNotificationSender {

	void send(String token, String title, String body) throws SendFailureException;

}
