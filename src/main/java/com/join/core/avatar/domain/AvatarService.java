package com.join.core.avatar.domain;

import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {

	AvatarInfo.ValidNickname isValid(AvatarCommand.ChangeNickname command);

	AvatarInfo.ValidNickname changeNickname(Long avatarId, AvatarCommand.ChangeNickname command);

	void changePhoto(Long avatarId, AvatarCommand.ChangePhoto command, MultipartFile image);

}
