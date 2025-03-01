package com.join.core.study.domain;

import com.join.core.rule.domain.Rule;
import com.join.core.address.domain.Address;
import com.join.core.avatar.domain.Avatar;
import com.join.core.category.domain.Category;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.common.util.TokenGenerator;
import com.join.core.schedule.domain.StudySchedule;
import com.join.core.study.constant.StudyEndReason;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.dto.request.StudyReRecruitRequest;
import com.join.core.study.dto.request.StudyRecruitRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.join.core.common.exception.ErrorCode.INVALID_PARAMETER;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study {

    private static final String STUDY_PREFIX = "std_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studyToken;

    @NotNull
    @Size(min = 5, max = 25)
    private String studyName;

    @NotNull
    private String title;

    @NotNull
    private String introduction;

    @NotNull
    private String content;

    @NotNull
    private int capacity;

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

    @Enumerated(EnumType.STRING)
    private StudyForm form;

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

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rule> rules;

    private String kakaoUrl;

    public Study(StudyRecruitRequest recruitRequest, Avatar writer, Address address, Category category) {
        if (writer == null)
            throw new InvalidParamException(INVALID_PARAMETER, "Study.writer");
        if (address == null)
            throw new InvalidParamException(INVALID_PARAMETER, "Study.address");
        if (category == null)
            throw new InvalidParamException(INVALID_PARAMETER, "Study.category");

        this.studyToken = TokenGenerator.randomCharacterWithPrefix(STUDY_PREFIX);
        this.title = recruitRequest.getTitle();
        this.capacity = recruitRequest.getCapacity();
        this.isRegular = recruitRequest.isRegular();
        this.form = recruitRequest.getForm();
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

    public void updateRecruitDetails(StudyReRecruitRequest reRecruitRequest) {
        this.capacity = reRecruitRequest.getCapacity();
        this.recruitEndDate = reRecruitRequest.getRecruitEndDate();
        this.title = reRecruitRequest.getTitle();
        this.introduction = reRecruitRequest.getIntroduction();
        this.content = reRecruitRequest.getContent();
        this.qualificationExp = reRecruitRequest.getQualificationExp();
        this.status = StudyStatus.RECRUITING;
    }

    public void addSchedules(List<StudySchedule> schedules) {
        this.schedules = schedules;
        for (StudySchedule schedule : schedules) {
            schedule.setStudy(this);
        }
    }

    public void endStudy(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
        this.endReason = StudyEndReason.COMPLETED;
        this.status = StudyStatus.COMPLETED;
    }

    public void addViewCount() {
        this.viewCnt++;
    }

    public void addBookmarkCount() {
        this.bookmarkCnt++;
    }

    public boolean isWriter(Long avatarId) {
        return getWriter().getId().equals(avatarId);
    }

    public void checkActiveStatus() {
        if (status != StudyStatus.ACTIVE) {
            throw new BadRequestException(ErrorCode.NOT_ACTIVE_STUDY);
        }
    }

    public void deleteBookmarkCount() {
        if (this.bookmarkCnt <= 0) {
            throw new IllegalStateException("북마크 수는 음수가 될 수 없습니다.");
        }
        this.bookmarkCnt--;
    }

    public void addRules(List<Rule> rules) {
        this.rules = rules;
        for (Rule rule : rules) {
            rule.setStudy(this);
        }
    }

}
