package com.join.core.avatar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.join.core.avatar.domain.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
	Optional<Avatar> findByAvatarToken(String avatarToken);

	@Query("select (count(a) > 0) from Avatar a where a.nickname = :nickname")
	boolean existsByNickname(@Param("nickname") String nickname);

}
