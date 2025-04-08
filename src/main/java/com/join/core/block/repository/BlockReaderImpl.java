package com.join.core.block.repository;

import com.join.core.block.domain.Block;
import com.join.core.block.service.BlockReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class BlockReaderImpl implements BlockReader {

    private final BlockRepository blockRepository;

    @Override
    public boolean existBySubjectTokenAndTargetToken(String subjectToken, String targetToken) {
        return blockRepository.existsBySubjectAvatarTokenAndTargetAvatarToken(subjectToken, targetToken);
    }

    @Override
    public Collection<Block> getAllBySubjectId(Long avatarId) {
        return blockRepository.findBySubjectId(avatarId);
    }
}
