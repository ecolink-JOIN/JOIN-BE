package com.join.core.avatar.domain;

import com.join.core.avatar.dto.response.MyPageInfoResponse;

public interface MyPageService {

	MyPageInfoResponse getMyPageInfo(Long avatarId);
}
