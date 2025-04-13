package com.join.core.notification.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.notification.constant.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @NotNull
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id", nullable = false)
    private Avatar writer;

    public Notification(String content, Study study, Avatar writer) {
        this.content = content;
        this.study = study;
        this.writer = writer;
        this.notificationType = NotificationType.STUDY_ANNOUNCEMENT;
    }

}
