package com.join.core.avatar.domain;

public interface NicknameValidator {

	AvatarInfo.ValidNickname validate(AvatarCommand.ChangeNickname command);

}
