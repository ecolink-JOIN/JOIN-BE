package com.join.core.auth.service;

import com.join.core.auth.domain.Role;
import com.join.core.auth.domain.User;
import com.join.core.auth.domain.UserInfo;

public interface UserReader {

	UserInfo.SigIn getSignInInfo(String email);

	Role getGuestRole();

	Role getUserRole();

	User getUser(Long userId);

}
