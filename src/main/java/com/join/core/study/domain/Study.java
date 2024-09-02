package com.join.core.study.domain;

import com.join.core.address.domain.Address;
import com.join.core.avatar.domain.Avatar;
import com.join.core.category.domain.Category;
import com.join.core.schedule.domain.StudySchedule;
import com.join.core.study.constant.StudyEndReason;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.dto.request.StudyRecruitRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 25)
    private String studyName;

    @NotNull
    private String introduction;

    @NotNull
    private String content;

    @NotNull
    private int capacity;

    @NotNull
    private String ruleExp;

    @NotNull
    private String qualificationExp;

    @NotNull
    private boolean isRegular;

    @NotNull
    private LocalDate recruitEndDate;

    @NotNull
    private LocalDate stDate;

    @NotNull
    private LocalDate endDate;

    private LocalDate actualEndDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    @NotNull
    private int viewCnt;

    @NotNull
    private int bookmarkCnt;

    @Enumerated(EnumType.STRING)
    private StudyEndReason endReason;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull
    @JoinColumn(name = "writer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Avatar writer;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudySchedule> schedules;

    public Study(StudyRecruitRequest recruitRequest, Avatar writer, Address address, Category category) {
        this.capacity = recruitRequest.getCapacity();
        this.isRegular = recruitRequest.isRegular();
        this.recruitEndDate = recruitRequest.getRecruitEndDate();
        this.stDate = recruitRequest.getStDate();
        this.endDate = recruitRequest.getEndDate();
        this.writer = writer;
        this.viewCnt = 0;
        this.bookmarkCnt = 0;
        this.address = address;
        this.category = category;
        this.status = StudyStatus.RECRUITING;
        this.studyName = recruitRequest.getStudyName();
        this.introduction = recruitRequest.getIntroduction();
        this.content = recruitRequest.getContent();
        this.ruleExp = recruitRequest.getRuleExp();
        this.qualificationExp = recruitRequest.getQualificationExp();
    }

    public void addSchedules(List<StudySchedule> schedules) {
        this.schedules = schedules;
        for (StudySchedule schedule : schedules) {
            schedule.setStudy(this);
        }
    }

    public void addViewCount() {
        this.viewCnt++;
    }

    public void addBookmarkCount() {
        this.bookmarkCnt++;
    }

}
