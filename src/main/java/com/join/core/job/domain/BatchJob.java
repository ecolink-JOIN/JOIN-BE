package com.join.core.job.domain;

import com.join.core.common.constant.DayType;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.study.domain.Study;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BatchJob extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private DayType day;

    private LocalTime time;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;

    public BatchJob(String content, DayType day, LocalTime time, Study study) {
        this.content = content;
        this.day = day;
        this.time = time;
        this.study = study;
    }

    public void update(String content, DayType day, LocalTime time) {
        this.content = (content != null) ? content : this.content;
        this.day = (day != null) ? day : this.day;
        this.time = (time != null) ? time : this.time;
    }

}