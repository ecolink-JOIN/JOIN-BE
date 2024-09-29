package com.join.core.avatar.domain;

public interface AvatarReader {
	Avatar getAvatarById(Long avatarId);

	boolean existsByNickname(String nickname);

	Avatar getById(Long id);

}
