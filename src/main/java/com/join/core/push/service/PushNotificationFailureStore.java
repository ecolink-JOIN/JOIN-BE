package com.join.core.push.service;

import com.join.core.push.domain.SendFailureException;

public interface PushNotificationFailureStore {

	void store(Long userId, SendFailureException exception);

}
