package com.join.core.auth.service;

import com.join.core.auth.domain.User;
import com.join.core.avatar.domain.Avatar;

public interface UserStore {

	User store(User user);

	Avatar store(Avatar avatar);

}
