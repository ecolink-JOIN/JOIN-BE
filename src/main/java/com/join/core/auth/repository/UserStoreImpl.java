package com.join.core.auth.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.User;
import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.domain.UserInfoMapper;
import com.join.core.auth.model.OAuth2Attributes;
import com.join.core.auth.service.UserReader;
import com.join.core.auth.service.UserStore;
import com.join.core.avatar.domain.AvatarCommand;
import com.join.core.avatar.domain.ProfilePhotoFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserStoreImpl implements UserStore {

	private final UserRepository userRepository;
	private final UserInfoMapper userInfoMapper;
	private final UserReader userReader;
	private final ProfilePhotoFactory profilePhotoFactory;

	@Transactional
	@Override
	public UserInfo.SigIn store(OAuth2Attributes attributes) {
		User user = attributes.toEntity();
		profilePhotoFactory.store(user.getAvatar(), new AvatarCommand.ChangePhoto(!attributes.hasImage()),
			attributes.getProfileImage());
		User savedUser = userRepository.save(user);
		savedUser.addRole(userReader.getGuestRole());
		return userInfoMapper.of(savedUser, true);
	}

}
