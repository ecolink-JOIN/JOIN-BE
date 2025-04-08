package com.join.core.block.service;

import com.join.core.block.domain.Block;

import java.util.Collection;

public interface BlockReader {

    boolean existBySubjectTokenAndTargetToken(String subjectToken, String targetToken);
    Collection<Block> getAllBySubjectId(Long avatarId);
}
