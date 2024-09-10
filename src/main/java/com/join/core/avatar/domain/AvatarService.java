package com.join.core.avatar.domain;

public interface AvatarService {

	AvatarInfo.ValidNickname isValid(AvatarCommand.ChangeNickname command);

}
