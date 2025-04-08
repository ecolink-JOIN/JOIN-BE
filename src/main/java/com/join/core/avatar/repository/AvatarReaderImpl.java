package com.join.core.avatar.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarInfo;
import com.join.core.avatar.domain.AvatarInfoMapper;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.enrollment.repository.EnrollmentQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AvatarReaderImpl implements AvatarReader {

    private final AvatarRepository avatarRepository;
    private final AvatarInfoMapper avatarInfoMapper;
    private final EnrollmentQueryRepository enrollmentQueryRepository;

    @Override
    public Avatar getAvatarById(Long id) {
        return avatarRepository.findById(id)
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

    @Transactional(readOnly = true)
    @Override
    public AvatarInfo.Self getInfo(Long id) {
        return avatarInfoMapper.of(getAvatarById(id));
    }

    @Override
    public Avatar getAvatarByAvatarToken(String avatarToken) {
        return avatarRepository.findByAvatarToken(avatarToken)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Avatar> findAvatarsExceptPendingByStudyId(Long studyId) {
        return enrollmentQueryRepository.findAvatarsExceptPendingByStudyId(studyId);
    }
}
