package com.join.core.auth.service;

import com.join.core.avatar.domain.Avatar;

public interface AvatarReader {
    Avatar getAvatarByToken(String avatarToken);

}
