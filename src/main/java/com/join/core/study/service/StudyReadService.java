package com.join.core.study.service;

import com.join.core.schedule.dto.response.StudyScheduleResponse;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.response.StudyDetailResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyReadService {

    private final StudyReader studyReader;

    public StudyReadService(StudyReader studyReader) {
        this.studyReader = studyReader;
    }

    @Transactional(readOnly = true)
    public StudyDetailResponse getStudyDetails(Long studyId) {
        Study study = studyReader.getStudyById(studyId);

        List<StudyScheduleResponse> schedules = study.getSchedules().stream()
                .map(schedule -> new StudyScheduleResponse(
                        schedule.getWeekOfDay(),
                        schedule.getStTime(),
                        schedule.getEndTime()))
                .toList();

        return new StudyDetailResponse(
                study.getStudyName(),
                study.getTitle(),
                study.getIntroduction(),
                study.getContent(),
                study.getCapacity(),
                study.isRegular(),
                study.getRecruitEndDate(),
                study.getStDate(),
                study.getEndDate(),
                study.getWriter().getId(),
                study.getWriter().getNickname(),
                schedules,
                study.getRuleExp(),
                study.getQualificationExp()
        );
    }

}
