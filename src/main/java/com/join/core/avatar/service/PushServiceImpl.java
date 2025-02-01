package com.join.core.avatar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.User;
import com.join.core.auth.service.UserReader;
import com.join.core.avatar.domain.AvatarCommand;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PushServiceImpl implements PushService {

	private final UserReader userReader;

	@Transactional
	@Override
	public void updatePushConsent(Long userId, AvatarCommand.ChangePushConsent command) {
		User user = userReader.getUser(userId);
		user.updatePushConsent(command.isConsent());
	}

}
