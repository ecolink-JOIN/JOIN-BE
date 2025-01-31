package com.join.core.evaluation.repository;

import com.join.core.evaluation.domain.Evaluation;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Optional<Evaluation> findByStudyAndRaterAndRatee(Study study, Avatar rater, Avatar ratee);

}