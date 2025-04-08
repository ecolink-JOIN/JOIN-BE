package com.join.core.meeting.domain;

import com.join.core.common.constant.DayType;
import com.join.core.schedule.domain.StudySchedule;
import com.join.core.schedule.domain.StudyScheduleReader;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MeetingAutoService {

    private final MeetingReader meetingReader;
    private final StudyScheduleReader studyScheduleReader;

    public void createRegularMeetings(Study study) {
        LocalDate startDate = study.getStDate();
        LocalDate endDate = study.getEndDate();
        List<DayType> weekDays = Arrays.asList(DayType.values());

        int nextMeetingNo = meetingReader.getNextMeetingNo(study);

        for (DayType dayType : weekDays) {
            LocalDate currentDate = startDate;

            while (!currentDate.isAfter(endDate)) {
                if (currentDate.getDayOfWeek() == dayType.getDayOfWeek()) {
                    StudySchedule schedule = studyScheduleReader.findScheduleByDayType(study, dayType);
                    if (schedule != null) {
                        Meeting meeting = Meeting.autoCreate(currentDate, schedule.getStTime(), schedule.getEndTime(), study);

                        meeting.updateMeetingNo(nextMeetingNo);
                        nextMeetingNo++;

                        study.getMeetings().add(meeting);
                    }
                }
                currentDate = currentDate.plusDays(1);
            }
        }

        List<Meeting> meetings = meetingReader.getMeetingsByStudy(study);
        int meetingCount = meetings.size();

        meetings.sort(Comparator.comparing(meeting ->
                LocalDate.of(meeting.getStudyDate().getYear(), meeting.getStudyDate().getMonth(), meeting.getStudyDate().getDayOfMonth())
                        .atTime(meeting.getStTime())));

        IntStream.range(0, meetingCount).forEach(index -> {
            Meeting meeting = meetings.get(index);
            meeting.updateMeetingNo(index + 1);
        });
    }

}
