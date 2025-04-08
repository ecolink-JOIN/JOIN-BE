package com.join.core.block.repository;

import com.join.core.block.domain.Block;
import com.join.core.block.service.BlockStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockStoreImpl implements BlockStore {

    private final BlockRepository blockRepository;

    @Override
    public Block save(Block block) {
        return blockRepository.save(block);
    }
}
