package com.join.core.avatar.domain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Order(2)
@Component
public class NicknameDuplicationValidator implements NicknameValidator {

	private final AvatarReader avatarReader;

	@Override
	public AvatarInfo.ValidNickname validate(AvatarCommand.ChangeNickname command) {
		if (avatarReader.existsByNickname(command.getNickname()))
			return new AvatarInfo.ValidNickname(false, "이미 사용 중인 닉네임이에요.");

		return AvatarInfo.ValidNickname.valid();
	}

}
