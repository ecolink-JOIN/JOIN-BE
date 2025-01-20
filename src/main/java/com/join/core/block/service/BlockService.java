package com.join.core.block.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.block.domain.Block;
import com.join.core.block.dto.response.CreateBlockResponse;
import com.join.core.block.mapper.BlockMapper;
import com.join.core.block.service.dto.CreateBlockParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockStore blockStore;
    private final AvatarReader avatarReader;
    private final BlockMapper blockMapper;

    public CreateBlockResponse createBlock(CreateBlockParams params) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(params.subjectAvatarToken());
        Avatar target = avatarReader.getAvatarByAvatarToken(params.targetAvatarToken());
        Block block = blockStore.save(blockMapper.toEntity(avatar, target, params.blockDate()));
        return blockMapper.toCreateBlockResponse(block);
    }
}
