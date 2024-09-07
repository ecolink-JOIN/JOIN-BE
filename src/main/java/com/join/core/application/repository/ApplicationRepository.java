package com.join.core.application.repository;

import com.join.core.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
