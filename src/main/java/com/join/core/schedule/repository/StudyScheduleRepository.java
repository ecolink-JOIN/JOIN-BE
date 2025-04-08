package com.join.core.schedule.repository;

import com.join.core.schedule.domain.StudySchedule;
import com.join.core.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyScheduleRepository extends JpaRepository<StudySchedule, Long> {
    List<StudySchedule> findByStudy(Study study);
}
