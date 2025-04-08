package com.join.core.evaluation.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.evaluation.domain.Evaluation;
import com.join.core.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    Optional<Evaluation> findByStudyAndRaterAndRatee(Study study, Avatar rater, Avatar ratee);

    @Query("SELECT e FROM Evaluation e WHERE e.study.id = :studyId AND e.ratee.id = :writerId")
    List<Evaluation> findByStudyAndWriter(Long studyId, Long writerId);

    @Query("SELECT e FROM Evaluation e WHERE e.study.id = :studyId AND e.ratee.id <> :writerId")
    List<Evaluation> findByStudyAndMembers(Long studyId, Long writerId);

    @Query("SELECT COALESCE(AVG(e.leaderScore), 0) FROM Evaluation e WHERE e.study.id = :studyId AND e.ratee.id = :writerId")
    double getLeaderScore(Long studyId, Long writerId);

    @Query("SELECT COALESCE(AVG(e.memberScore), 0) FROM Evaluation e WHERE e.study.id = :studyId AND e.ratee.id <> :writerId")
    double getMemberScore(Long studyId, Long writerId);

}