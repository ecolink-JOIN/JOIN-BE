package com.join.core.avatar.repository;

import com.join.core.avatar.domain.AvatarReader;
import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class AvatarReaderImpl implements AvatarReader {

    private final AvatarRepository avatarRepository;

    @Transactional(readOnly = true)
    @Override
    public Avatar getAvatarByToken(String avatarToken) {
        return avatarRepository.findByAvatarToken(avatarToken)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByNickname(String nickname) {
        return avatarRepository.existsByNickname(nickname);
    }

    @Override
    public Avatar getById(Long id) {
        return avatarRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_FOUND));
    }

}
