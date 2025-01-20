package com.join.core.block.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.block.domain.Block;
import com.join.core.block.dto.response.CreateBlockResponse;
import com.join.core.block.mapper.BlockMapper;
import com.join.core.block.service.dto.CreateBlockParams;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.common.exception.impl.EntityAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockStore blockStore;
    private final BlockReader blockReader;
    private final AvatarReader avatarReader;
    private final BlockMapper blockMapper;

    public CreateBlockResponse createBlock(CreateBlockParams params) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(params.subjectAvatarToken());
        Avatar target = avatarReader.getAvatarByAvatarToken(params.targetAvatarToken());
        checkDuplicated(avatar.getAvatarToken(), target.getAvatarToken());
        checkTarget(avatar.getAvatarToken(), target.getAvatarToken());
        Block block = blockStore.save(blockMapper.toEntity(avatar, target, params.blockDate()));
        return blockMapper.toCreateBlockResponse(block);
    }

    private void checkDuplicated(String subjectAvatarToken, String targetAvatarToken) {
        if (blockReader.existBySubjectTokenAndTargetToken(subjectAvatarToken, targetAvatarToken)) {
            throw new EntityAlreadyExistsException(ErrorCode.BLOCK_ALREADY_EXISTS);
        }
    }

    private void checkTarget(String subjectAvatarToken, String targetAvatarToken) {
        if (subjectAvatarToken.equals(targetAvatarToken)) {
            throw new BadRequestException(ErrorCode.SELF_BLOCK_NOT_ALLOWED);
        }
    }
}
