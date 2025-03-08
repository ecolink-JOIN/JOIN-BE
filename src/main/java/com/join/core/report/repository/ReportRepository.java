package com.join.core.report.repository;

import com.join.core.report.domain.Report;
import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r WHERE r.reporter = :reporter AND r.study = :study AND r.createdDate > :timeLimit ORDER BY r.createdDate DESC")
    Report findRecentReport(@Param("reporter") Avatar reporter, @Param("study") Study study, @Param("timeLimit") LocalDateTime timeLimit);

}