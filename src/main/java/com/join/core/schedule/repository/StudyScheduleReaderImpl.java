package com.join.core.schedule.repository;

import com.join.core.common.constant.DayType;
import com.join.core.schedule.domain.StudySchedule;
import com.join.core.schedule.domain.StudyScheduleReader;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StudyScheduleReaderImpl implements StudyScheduleReader {

    private final StudyScheduleRepository studyScheduleRepository;

    @Override
    public StudySchedule findScheduleByDayType(Study study, DayType dayType) {
        List<StudySchedule> schedules = studyScheduleRepository.findByStudy(study);
        return schedules.stream()
                .filter(schedule -> schedule.getWeekOfDay() == dayType)
                .findFirst()
                .orElse(null);
    }

}
