package com.join.core.evaluation.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.evaluation.domain.Evaluation;
import com.join.core.evaluation.domain.EvaluationStore;
import com.join.core.evaluation.dto.request.EvaluationRequest;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.join.core.common.exception.ErrorCode.INVALID_PARAMETER;

@Component
@RequiredArgsConstructor
public class EvaluationStoreImpl implements EvaluationStore {

    private final EvaluationRepository evaluationRepository;

    @Override
    public void createEvaluation(Study study, Avatar rater, Avatar ratee, EvaluationRequest request) {
        Evaluation existingEvaluation = evaluationRepository.findByStudyAndRaterAndRatee(study, rater, ratee)
                .orElse(null);

        if (existingEvaluation != null) {
            throw new InvalidParamException(INVALID_PARAMETER, "이미 평가한 대상입니다.");
        }

        Evaluation evaluation = request.toEntity(study, rater, ratee);
        evaluationRepository.save(evaluation);
    }

}
