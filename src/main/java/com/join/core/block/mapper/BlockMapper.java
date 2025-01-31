package com.join.core.block.mapper;

import com.join.core.avatar.domain.Avatar;
import com.join.core.block.domain.Block;
import com.join.core.block.dto.response.CreateBlockResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BlockMapper {

    public Block toEntity(Avatar avatar, Avatar target, LocalDate blockDate) {
        return Block.builder()
                .subject(avatar)
                .target(target)
                .blockDate(blockDate)
                .build();
    }

    public CreateBlockResponse toCreateBlockResponse(Block block) {
        return new CreateBlockResponse(block.getId(), block.getTarget().getAvatarToken(), block.getBlockDate());
    }
}
