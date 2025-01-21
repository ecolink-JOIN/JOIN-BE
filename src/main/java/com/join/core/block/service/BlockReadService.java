package com.join.core.block.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.block.dto.response.BlockMemberResponse;
import com.join.core.block.mapper.BlockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class BlockReadService {

    private final BlockReader blockReader;
    private final AvatarReader avatarReader;
    private final BlockMapper blockMapper;

    @Transactional(readOnly = true)
    public Collection<BlockMemberResponse> getBlocks(String avatarToken) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(avatarToken);
        
        return blockReader.getAllBySubjectId(avatar.getId()).stream()
                .map(blockMapper::toBlockMemberResponse)
                .toList();
    }
}
