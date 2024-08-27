package com.join.core.auth.repository;

import org.springframework.stereotype.Component;

import com.join.core.auth.domain.User;
import com.join.core.auth.service.UserStore;
import com.join.core.avatar.domain.Avatar;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserStoreImpl implements UserStore {

	private final UserRepository userRepository;
	private final AvatarRepository avatarRepository;

	@Override
	public User store(User user) {
		return userRepository.save(user);
	}

	@Override
	public Avatar store(Avatar avatar) {
		return avatarRepository.save(avatar);
	}
}
