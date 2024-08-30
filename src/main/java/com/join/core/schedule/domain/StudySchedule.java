package com.join.core.schedule.domain;

import com.join.core.common.constant.DayType;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.study.domain.Study;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StudySchedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DayType weekOfDay;

    @NotNull
    private LocalTime stTime;

    @NotNull
    private LocalTime endTime;

    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)  // 외래 키 설정
    private Study study;

    public StudySchedule(DayType weekOfDay, LocalTime stTime, LocalTime endTime) {
        this.weekOfDay = weekOfDay;
        this.stTime = stTime;
        this.endTime = endTime;
    }

}