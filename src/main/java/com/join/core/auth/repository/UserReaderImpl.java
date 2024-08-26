package com.join.core.auth.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.constant.RoleType;
import com.join.core.auth.domain.Role;
import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.domain.UserInfoMapper;
import com.join.core.auth.service.UserReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserReaderImpl implements UserReader {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserInfoMapper userInfoMapper;

	@Transactional(readOnly = true)
	@Override
	public UserInfo.SigIn getSignInInfo(String email) {
		return userRepository.findByEmail(email)
			.map(user -> userInfoMapper.of(user, false))
			.orElseGet(UserInfo.SigIn::unregistered);
	}

	@Transactional(readOnly = true)
	@Override
	public Role getGuestRole() {
		return getRole(RoleType.GUEST);
	}

	@Transactional(readOnly = true)
	@Override
	public Role getUserRole() {
		return getRole(RoleType.USER);
	}

	private Role getRole(RoleType type) {
		return roleRepository.findByType(type)
			.orElse(roleRepository.save(new Role(type)));
	}

}
