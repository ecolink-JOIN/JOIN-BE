package com.join.core.avatar.repository;

import com.join.core.avatar.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByAvatarToken(String avatarToken);

}
