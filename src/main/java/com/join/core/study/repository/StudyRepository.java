package com.join.core.study.repository;

import com.join.core.study.domain.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {
    Optional<Study> findByStudyToken(String studyToken);
    Page<Study> findAllByTitleContaining(String title, Pageable pageable);
}
