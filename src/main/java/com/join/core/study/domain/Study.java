package com.join.core.study.domain;

import com.join.core.enrollment.domain.Enrollment;
import com.join.core.address.domain.Address;
import com.join.core.category.domain.Category;
import com.join.core.study.constant.StudyEndReason;
import com.join.core.study.constant.StudyStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private LocalDate stDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private LocalDate actualEndDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    @NotNull
    private int viewCnt;

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

    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

}
