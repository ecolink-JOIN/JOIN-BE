package com.join.core.evaluation.domain;

import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import com.join.core.evaluation.dto.request.EvaluationRequest;
import com.join.core.study.service.StudyReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final StudyReader studyReader;
    private final AvatarReader avatarReader;
    private final EnrollmentReader enrollmentReader;
    private final EvaluationStore evaluationStore;

    @Transactional
    public void evaluate(EvaluationRequest request, Long raterId) {
        Study study = studyReader.validateStudyCompletion(request.getStudyToken());
        Avatar ratee = avatarReader.getAvatarByAvatarToken(request.getRateeToken());

        Avatar rater = avatarReader.getAvatarById(raterId);

        if (rater.getId().equals(ratee.getId())) {
            throw new InvalidParamException(ErrorCode.INVALID_PARAMETER, "평가자와 평가대상자는 같을 수 없습니다.");
        }

        enrollmentReader.validateEnrollment(ratee.getId(), request.getStudyToken());
        evaluationStore.createEvaluation(study, rater, ratee, request);
    }

}
