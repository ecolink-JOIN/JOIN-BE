package com.join.core.enrollment.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.constant.StudyRole;
import com.join.core.study.domain.Study;
import com.join.core.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Enrollment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @Size(min = 10, max = 150)
    private String endReason;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StudyRole role;

    @NotNull
    private LocalDateTime enrolledDate;

    private LocalDateTime endDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    public Enrollment(Study study, Avatar avatar, EnrollmentStatus status, LocalDateTime enrolledDate,
                      LocalDateTime endDate, String endReason, StudyRole role) {
        this.study = study;
        this.avatar = avatar;
        this.status = status;
        this.enrolledDate = enrolledDate;
        this.endDate = endDate;
        this.endReason = endReason;
        this.role = role;
    }

    public void withdraw() {
        this.status = EnrollmentStatus.LEFT;
    }
}
