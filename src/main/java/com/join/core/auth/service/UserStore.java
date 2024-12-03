package com.join.core.auth.service;

import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.model.OAuth2Attributes;

public interface UserStore {

	UserInfo.SigIn store(OAuth2Attributes attributes);

}
