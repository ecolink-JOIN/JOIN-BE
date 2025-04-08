package com.join.core.evaluation.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.evaluation.domain.Evaluation;
import com.join.core.evaluation.domain.EvaluationStore;
import com.join.core.evaluation.dto.request.EvaluationRequest;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.join.core.common.exception.ErrorCode.INVALID_PARAMETER;

@Component
@RequiredArgsConstructor
public class EvaluationStoreImpl implements EvaluationStore {

    private final EvaluationRepository evaluationRepository;

    @Override
    @Transactional
    public void createEvaluation(Study study, Avatar rater, Avatar ratee, EvaluationRequest request) {
        evaluationRepository.findByStudyAndRaterAndRatee(study, rater, ratee)
                .ifPresent(e -> {
                    throw new InvalidParamException(INVALID_PARAMETER, "이미 평가한 대상입니다.");
                });

        Evaluation evaluation = request.toEntity(study, rater, ratee);
        evaluationRepository.save(evaluation);

        updateScores(study);
    }

    private void updateScores(Study study) {
        Avatar writer = study.getWriter();

        double leaderScore = calculateAverageScore(evaluationRepository.findByStudyAndWriter(study.getId(), writer.getId()));
        double memberScore = calculateAverageScore(evaluationRepository.findByStudyAndMembers(study.getId(), writer.getId()));

        for (Evaluation evaluation : evaluationRepository.findByStudyAndWriter(study.getId(), writer.getId())) {
            evaluation.updateScores(leaderScore, evaluation.getMemberScore());
        }

        for (Evaluation evaluation : evaluationRepository.findByStudyAndMembers(study.getId(), writer.getId())) {
            evaluation.updateScores(evaluation.getLeaderScore(), memberScore);
        }
    }

    private double calculateAverageScore(List<Evaluation> evaluations) {
        return Math.round(
                evaluations.stream()
                        .mapToDouble(e -> (e.getSincerity() + e.getFamiliarity() + e.getEffect()) / 3.0)
                        .average()
                        .orElse(0.0) * 10
        ) / 10.0;
    }

}