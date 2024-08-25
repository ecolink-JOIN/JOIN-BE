package com.join.core.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.join.core.auth.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u "
		   + "from User u "
		   + "join fetch Avatar a "
		   + "join fetch a.photo p "
		   + "join fetch u.userRoles ur "
		   + "join fetch ur.role "
		   + "where u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);
}
