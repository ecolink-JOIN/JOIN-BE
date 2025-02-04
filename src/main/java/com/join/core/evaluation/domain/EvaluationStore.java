package com.join.core.evaluation.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.evaluation.dto.request.EvaluationRequest;
import com.join.core.study.domain.Study;

public interface EvaluationStore {
    void createEvaluation(Study study, Avatar rater, Avatar ratee, EvaluationRequest request);

}
