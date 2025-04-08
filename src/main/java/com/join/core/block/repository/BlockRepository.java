package com.join.core.block.repository;

import com.join.core.block.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BlockRepository extends JpaRepository<Block, Long> {

    boolean existsBySubjectAvatarTokenAndTargetAvatarToken(String subjectAvatarToken, String targetAvatarToken);
    Collection<Block> findBySubjectId(Long subjectId);
}
