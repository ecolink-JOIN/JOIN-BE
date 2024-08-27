package com.join.core.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.join.core.avatar.domain.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
