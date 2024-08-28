package com.join.core.auth.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.User;
import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.domain.UserInfoMapper;
import com.join.core.auth.model.OAuth2Attributes;
import com.join.core.auth.service.UserReader;
import com.join.core.auth.service.UserSeriesFactory;
import com.join.core.auth.service.UserStore;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.ProfilePhoto;
import com.join.core.file.domain.ImageFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserSeriesFactoryImpl implements UserSeriesFactory {

	private static final String DEFAULT_PROFILE_PHOTO_URL = "https://fastly.picsum.photos/id/688/200/200.jpg?hmac=SPM6DXITCd9R3P5BMqgFMw6QdW-SJ2mPKUvq2g9eF-g";
	private final UserStore userStore;
	private final UserReader userReader;
	private final UserInfoMapper userInfoMapper;

	@Transactional
	@Override
	public UserInfo.SigIn store(User user, OAuth2Attributes attributes) {
		String photoUrl = attributes.hasImage() ? attributes.getProfileImage() : DEFAULT_PROFILE_PHOTO_URL;
		ProfilePhoto photo = new ProfilePhoto(ImageFile.externalImage(photoUrl));
		userStore.store(new Avatar(user, photo));
		user.addRole(userReader.getGuestRole());
		return userInfoMapper.of(user, true);
	}

}
