package com.join.core.avatar.domain;

import org.springframework.web.multipart.MultipartFile;

public interface ProfilePhotoFactory {

	void store(Avatar avatar, AvatarCommand.ChangePhoto command, MultipartFile image);

	void store(Avatar avatar, AvatarCommand.ChangePhoto command, String imageUrl);

}
