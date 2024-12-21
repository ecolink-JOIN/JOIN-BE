package com.join.core.meeting.domain;

import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.meeting.constant.MeetingStatus;
import com.join.core.study.domain.Study;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.join.core.common.exception.ErrorCode.*;
import static com.join.core.meeting.constant.MeetingStatus.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int meetingNo;

    @NotNull
    private LocalDate studyDate;

    @NotNull
    private LocalTime stTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MeetingStatus status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;

    public Meeting(LocalDate studyDate, LocalTime stTime, LocalTime endTime, Study study) {
        if (studyDate == null) {
            throw new InvalidParamException(INVALID_PARAMETER, "Meeting.studyDate");
        }
        if (stTime == null) {
            throw new InvalidParamException(INVALID_PARAMETER, "Meeting.stTime");
        }
        if (endTime == null) {
            throw new InvalidParamException(INVALID_PARAMETER, "Meeting.endTime");
        }
        if (study == null) {
            throw new InvalidParamException(INVALID_PARAMETER, "Meeting.study");
        }
        this.meetingNo = 0;
        this.studyDate = studyDate;
        this.stTime = stTime;
        this.endTime = endTime;
        this.status = WAITING;
        this.study = study;
    }

    public void updateMeetingNo(int meetingNo) {
        this.meetingNo = meetingNo;
    }

    public boolean isWithinMeetingTime(LocalDateTime now) {
        if (!studyDate.isEqual(now.toLocalDate())) {
            return false;
        }

        LocalTime currentTime = now.toLocalTime();
        LocalTime meetingStart = stTime.minusMinutes(10);
        return currentTime.isAfter(meetingStart) && currentTime.isBefore(endTime);
    }
}
