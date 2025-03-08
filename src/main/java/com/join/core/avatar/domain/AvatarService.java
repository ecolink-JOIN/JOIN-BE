package com.join.core.avatar.domain;

import org.springframework.web.multipart.MultipartFile;

import com.join.core.avatar.dto.ChangePreferenceRequest;

public interface AvatarService {

	AvatarInfo.ValidNickname isValid(AvatarCommand.ChangeNickname command);

	AvatarInfo.ValidNickname changeNickname(Long avatarId, AvatarCommand.ChangeNickname command);

	void changePhoto(Long avatarId, AvatarCommand.ChangePhoto command, MultipartFile image);

	AvatarInfo.Self getAvatarInfo(Long avatarId);

	void changePreference(Long avatarId, ChangePreferenceRequest request);
}
