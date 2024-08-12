package com.join.core.avatar.domain;

import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.enrollment.domain.Enrollment;
import com.join.core.evaluation.domain.Evaluation;
import com.join.core.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Avatar extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 2, max = 7)
    private String nickname;

    private String preferenceId;

    @NotNull
    private int totalRating;

    @NotNull
    private int ratingCnt;

    @NotNull
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "ratee", fetch = FetchType.LAZY)
    private List<Evaluation> evaluationsReceived;

    @OneToMany(mappedBy = "rater", fetch = FetchType.LAZY)
    private List<Evaluation> evaluationsGiven;

    @OneToMany(mappedBy = "avatar", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

}
