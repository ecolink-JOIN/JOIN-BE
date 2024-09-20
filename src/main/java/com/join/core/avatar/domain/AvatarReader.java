package com.join.core.avatar.domain;

public interface AvatarReader {
	Avatar getAvatarByToken(String avatarToken);

	boolean existsByNickname(String nickname);

	Avatar getById(Long id);

}
