package com.join.core.evaluation.domain;

import com.join.core.evaluation.dto.response.EvaluationScore;

public interface EvaluationReader {
    EvaluationScore getEvaluationScores(Long studyId, Long writerId);

}