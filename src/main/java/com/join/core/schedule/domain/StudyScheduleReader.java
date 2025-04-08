package com.join.core.schedule.domain;

import com.join.core.common.constant.DayType;
import com.join.core.study.domain.Study;

public interface StudyScheduleReader {
    StudySchedule findScheduleByDayType(Study study, DayType dayType);
}
