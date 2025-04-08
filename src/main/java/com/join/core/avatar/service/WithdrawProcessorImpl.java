package com.join.core.avatar.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.User;
import com.join.core.avatar.domain.Avatar;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class WithdrawProcessorImpl implements WithdrawProcessor {

	@Transactional
	@Override
	public void process(Avatar avatar) {
		User user = avatar.getUser();
		user.withdraw();
	}

}
