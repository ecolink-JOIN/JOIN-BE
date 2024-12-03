package com.join.core.application.domain;

import com.join.core.application.constant.ApplicationStatus;
import com.join.core.avatar.domain.Avatar;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.study.domain.Study;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @NotNull
    private LocalDate appDate;

    @NotNull
    @Size(min = 10)
    private String introduction;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id", nullable = false)
    private Avatar avatar;

    public Application(Study study, Avatar avatar, LocalDate appDate, String introduction) {
        this.study = study;
        this.avatar = avatar;
        this.appDate = appDate;
        this.introduction = introduction;
        this.status = ApplicationStatus.PENDING;
    }

    public void accept() {
        if (this.status == ApplicationStatus.PENDING) {
            this.status = ApplicationStatus.APPROVED;
        } else {
            throw new IllegalStateException("이미 처리된 지원입니다.");
        }
    }

    public void reject() {
        if (this.status == ApplicationStatus.PENDING) {
            this.status = ApplicationStatus.REJECTED;
        } else {
            throw new IllegalStateException("이미 처리된 지원입니다.");
        }
    }

}