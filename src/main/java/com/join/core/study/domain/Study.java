package com.join.core.study.domain;

import com.join.core.address.domain.Address;
import com.join.core.avatar.domain.Avatar;
import com.join.core.category.domain.Category;
import com.join.core.study.constant.StudyEndReason;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.dto.request.StudyDescriptionRequest;
import com.join.core.study.dto.request.StudyInfoRequest;
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

    public Study(StudyInfoRequest infoRequest, Avatar writer, Address address, Category category) {
        this.capacity = infoRequest.getCapacity();
        this.isRegular = infoRequest.isRegular();
        this.recruitEndDate = infoRequest.getRecruitEndDate();
        this.stDate = infoRequest.getStDate();
        this.endDate = infoRequest.getEndDate();
        this.writer = writer;
        this.viewCnt = 0;
        this.bookmarkCnt = 0;
        this.address = address;
        this.category = category;
        this.status = StudyStatus.RECRUITING;
    }

    public void addStudyDescription(StudyDescriptionRequest descriptionRequest) {
        this.studyName = descriptionRequest.getStudyName();
        this.introduction = descriptionRequest.getIntroduction();
        this.content = descriptionRequest.getContent();
        this.ruleExp = descriptionRequest.getRuleExp();
        this.qualificationExp = descriptionRequest.getQualificationExp();
    }

    public void addViewCount() {
        this.viewCnt++;
    }

    public void addBookmarkCount() {
        this.bookmarkCnt++;
    }

}
