package com.join.core.application.repository;

import com.join.core.application.domain.Application;
import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByStudyAndAvatar(Study study, Avatar avatar);

}
