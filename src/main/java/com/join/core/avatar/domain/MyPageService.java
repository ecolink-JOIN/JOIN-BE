package com.join.core.avatar.domain;

import com.join.core.avatar.dto.response.MyJoinedStudyResponse;
import com.join.core.avatar.dto.response.MyManagedStudyInfoResponse;
import com.join.core.avatar.dto.response.MyPageInfoResponse;
import com.join.core.study.dto.response.CustomStudyResponse;

import java.util.Collection;
import java.util.List;

public interface MyPageService {

	MyPageInfoResponse getMyPageInfo(Long avatarId);
	List<MyManagedStudyInfoResponse> getMyManagedStudies(Long avatarId);
	MyJoinedStudyResponse getMyJoinedStudies(Long avatarId);
	Collection<CustomStudyResponse> getMyInterestStudies(Long avatarId);
}
