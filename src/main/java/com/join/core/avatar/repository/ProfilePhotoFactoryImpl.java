package com.join.core.avatar.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarCommand;
import com.join.core.avatar.domain.ProfilePhoto;
import com.join.core.avatar.domain.ProfilePhotoFactory;
import com.join.core.file.constant.FilePath;
import com.join.core.file.domain.ImageFile;
import com.join.core.file.service.SinglePhotoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProfilePhotoFactoryImpl implements ProfilePhotoFactory {

	private static final String DEFAULT_PROFILE_PHOTO_URL = "https://fastly.picsum.photos/id/688/200/200.jpg?hmac=SPM6DXITCd9R3P5BMqgFMw6QdW-SJ2mPKUvq2g9eF-g";
	private final SinglePhotoService singlePhotoService;

	@Transactional
	@Override
	public void store(Avatar avatar, AvatarCommand.ChangePhoto command, MultipartFile image) {
		if (command.isDefaultPhoto()) {
			singlePhotoService.changePhoto(avatar,
				new ProfilePhoto(ImageFile.externalImage(DEFAULT_PROFILE_PHOTO_URL)));
			return;
		}
		singlePhotoService.changePhoto(image, avatar, ProfilePhoto::new, FilePath.PROFILE_PHOTO);
	}

	@Transactional
	@Override
	public void store(Avatar avatar, AvatarCommand.ChangePhoto command, String imageUrl) {
		if (command.isDefaultPhoto()) {
			singlePhotoService.changePhoto(avatar,
				new ProfilePhoto(ImageFile.externalImage(DEFAULT_PROFILE_PHOTO_URL)));
			return;
		}
		singlePhotoService.changePhoto(avatar, new ProfilePhoto(ImageFile.externalImage(imageUrl)));
	}
}
