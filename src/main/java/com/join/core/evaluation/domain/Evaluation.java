package com.join.core.evaluation.domain;

import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Evaluation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int sincerity;

    @NotNull
    private int familiarity;

    @NotNull
    private int effect;

    @Column(nullable = false)
    private double leaderScore;

    @Column(nullable = false)
    private double memberScore;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ratee_id")
    private Avatar ratee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rater_id")
    private Avatar rater;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    public Evaluation(int sincerity, int familiarity, int effect, Avatar ratee, Avatar rater, Study study) {
        this.sincerity = sincerity;
        this.familiarity = familiarity;
        this.effect = effect;
        this.ratee = ratee;
        this.rater = rater;
        this.study = study;
        this.leaderScore = 0;
        this.memberScore = 0;
    }

    public void updateScores(double leaderScore, double memberScore) {
        this.leaderScore = leaderScore;
        this.memberScore = memberScore;
    }

}
