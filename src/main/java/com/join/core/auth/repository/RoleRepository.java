package com.join.core.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.join.core.auth.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("select r from Role r where r.type = ?1")
	Optional<Role> findByType(@NonNull Role.Type roleType);
}
