package com.join.core.auth.repository;

import com.join.core.avatar.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByAvatarToken(String avatarToken);

}
