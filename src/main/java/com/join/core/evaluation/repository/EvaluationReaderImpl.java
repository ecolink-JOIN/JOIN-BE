package com.join.core.evaluation.repository;

import com.join.core.evaluation.domain.EvaluationReader;
import com.join.core.evaluation.dto.response.EvaluationScore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationReaderImpl implements EvaluationReader {

    private final EvaluationRepository evaluationRepository;

    @Override
    public EvaluationScore getEvaluationScores(Long studyId, Long writerId) {
        double leaderScore = evaluationRepository.getLeaderScore(studyId, writerId);
        double memberScore = evaluationRepository.getMemberScore(studyId, writerId);
        return new EvaluationScore(leaderScore, memberScore);
    }

}