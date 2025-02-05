package com.join.core.report.repository;

import com.join.core.report.domain.Report;
import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r " +
            "WHERE r.reporter = :reporter " +
            "AND r.study = :study " +
            "AND r.createdAt >= :timeLimit " +
            "ORDER BY r.createdAt DESC")
    Report findRecent(@Param("reporter") Avatar reporter,
                      @Param("study") Study study,
                      @Param("timeLimit") LocalDateTime timeLimit);

}
