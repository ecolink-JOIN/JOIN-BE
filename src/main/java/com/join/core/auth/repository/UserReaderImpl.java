package com.join.core.auth.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.Role;
import com.join.core.auth.domain.User;
import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.domain.UserInfoMapper;
import com.join.core.auth.service.UserReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class UserReaderImpl implements UserReader {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserInfoMapper userInfoMapper;

	@Override
	public UserInfo.SigIn getSignInInfo(String email) {
		return userRepository.findByEmail(email)
			.map(user -> userInfoMapper.of(user, false))
			.orElseGet(UserInfo.SigIn::unregistered);
	}

	@Transactional
	@Override
	public Role getGuestRole() {
		return getRole(Role.Type.GUEST);
	}

	@Transactional
	@Override
	public Role getUserRole() {
		return getRole(Role.Type.USER);
	}

	@Override
	public User getUser(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
	}

	private Role getRole(Role.Type type) {
		return roleRepository.findByType(type)
			.orElse(roleRepository.save(new Role(type)));
	}

}
