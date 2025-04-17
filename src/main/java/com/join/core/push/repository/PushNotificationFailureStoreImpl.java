package com.join.core.push.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.push.domain.PushNotificationFailure;
import com.join.core.push.domain.SendFailureException;
import com.join.core.push.service.PushNotificationFailureStore;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Component
public class PushNotificationFailureStoreImpl implements PushNotificationFailureStore {

	private final PushNotificationFailureRepository pushNotificationFailureRepository;

	@Override
	public void store(Long userId, SendFailureException exception) {
		pushNotificationFailureRepository.save(new PushNotificationFailure(userId, exception));
	}

}
