package com.join.core.report.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.report.constant.ReportType;
import com.join.core.study.domain.Study;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportType reportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Avatar writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private Avatar reporter;

    @Column(nullable = false, length = 300)
    private String reason;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Report(ReportType reportType, Study study, Avatar writer, Avatar reporter, String reason) {
        this.reportType = reportType;
        this.study = study;
        this.writer = writer;
        this.reporter = reporter;
        this.reason = reason;
        this.createdAt = LocalDateTime.now();
    }

}