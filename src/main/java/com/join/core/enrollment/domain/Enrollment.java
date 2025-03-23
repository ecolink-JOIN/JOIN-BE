package com.join.core.enrollment.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.LeaderForbiddenException;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.constant.StudyRole;
import com.join.core.enrollment.exception.AlreadyNotJoinedStudyException;
import com.join.core.study.domain.Study;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    public void delegateLeader() {
        if (!role.isLeader()) {
            throw new LeaderForbiddenException(ErrorCode.LEADER_ONLY_ACCESS);
        }
        this.role = StudyRole.MEMBER;
    }

    public void appointLeader() {
        if (role.isLeader()) {
            throw new BadRequestException(ErrorCode.ALREADY_STUDY_LEADER);
        }
        this.role = StudyRole.LEADER;
    }

    public void forcedOut() {
        if (!status.equals(EnrollmentStatus.JOINED)) {
            throw new AlreadyNotJoinedStudyException();
        }
        this.status = EnrollmentStatus.FORCED_OUT;
    }
}
