package com.join.core.auth.service;

import com.join.core.auth.domain.User;
import com.join.core.auth.domain.UserInfo;
import com.join.core.auth.model.OAuth2Attributes;

public interface UserSeriesFactory {

	UserInfo.SigIn store(User user, OAuth2Attributes attributes);

}
