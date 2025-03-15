package com.join.core.fine.domain;

import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.fine.constant.FineReason;
import com.join.core.study.domain.Study;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FineRule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FineReason reason;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;
}
