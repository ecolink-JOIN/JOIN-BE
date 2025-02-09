package com.join.core.avatar.domain;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AvatarReader {
	Avatar getAvatarById(Long avatarId);

	boolean existsByNickname(String nickname);

	Avatar getById(Long id);

	AvatarInfo.Self getInfo(Long id);

	Avatar getAvatarByAvatarToken(String avatarToken);

	List<Avatar> findAvatarsExceptPendingByStudyId(Long studyId);

	@Transactional(readOnly = true)
	List<Avatar> findJoinedStudyAvatarsByStudyId(Long studyId);
}
