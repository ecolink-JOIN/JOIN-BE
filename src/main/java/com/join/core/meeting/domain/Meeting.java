package com.join.core.meeting.domain;

import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.meeting.constant.MeetingStatus;
import com.join.core.study.domain.Study;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.join.core.common.exception.ErrorCode.INVALID_PARAMETER;
import static com.join.core.common.exception.ErrorCode.OUT_OF_PROOF_TIME;
import static com.join.core.meeting.constant.MeetingStatus.WAITING;

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

    public boolean isAfterMeetingTime(LocalDateTime now) {
        if (!studyDate.isEqual(now.toLocalDate())) {
            return false;
        }

        LocalTime currentTime = now.toLocalTime();
        LocalTime meetingStart = stTime.minusMinutes(10);
        return currentTime.isAfter(meetingStart);
    }

    public boolean isLate(LocalDateTime now) {
        LocalTime currentTime = now.toLocalTime();
        return currentTime.isAfter(stTime);
    }

    public void checkProofTime(LocalDateTime provenDate) {
        LocalTime currentTime = provenDate.toLocalTime();
        LocalTime proofEndTime = LocalTime.MIDNIGHT.minusNanos(1);

        if (!studyDate.isEqual(provenDate.toLocalDate()) ||
                !currentTime.isAfter(stTime) ||
                !currentTime.isBefore(proofEndTime)) {
            throw new BadRequestException(OUT_OF_PROOF_TIME);
        }
    }

    public static Meeting autoCreate(LocalDate studyDate, LocalTime stTime, LocalTime endTime, Study study) {
        return new Meeting(studyDate, stTime, endTime, study);
    }

    public void cancelMeeting() {
        this.status = MeetingStatus.CANCELED;
    }
}
