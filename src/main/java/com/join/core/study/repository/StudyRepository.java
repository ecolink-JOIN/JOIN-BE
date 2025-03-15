package com.join.core.study.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.join.core.study.constant.StudyStatus;
import com.join.core.study.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {
    Optional<Study> findByStudyToken(String studyToken);
    Page<Study> findAllByTitleContaining(String title, Pageable pageable);
    Optional<Study> findByStudyTokenAndStatus(String studyToken, StudyStatus status);
    Optional<Study> findByIdAndStatus(Long studyId, StudyStatus status);
}