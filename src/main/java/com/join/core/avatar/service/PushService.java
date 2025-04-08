package com.join.core.avatar.service;

import com.join.core.avatar.domain.AvatarCommand;

public interface PushService {

	void updatePushConsent(Long userId, AvatarCommand.ChangePushConsent command);

}
