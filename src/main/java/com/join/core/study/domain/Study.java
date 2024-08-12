package com.join.core.study.domain;

import com.join.core.enrollment.domain.Enrollment;
import com.join.core.study.constant.Address;
import com.join.core.study.constant.Category;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    private String status;

    @NotNull
    private int viewCnt;

    private String endReason;

    @Valid
    @NotNull
    @Embedded
    private Address address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

}
