package com.join.core.meeting.dto.response;

import com.join.core.meeting.constant.MeetingStatus;
import com.join.core.meeting.domain.Meeting;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class MeetingResponse {
    @Schema(description = "회차 순서", example = "1")
    private int meetingNo;

    @Schema(description = "스터디 날짜", example = "2024-12-29")
    private LocalDate studyDate;

    @Schema(description = "스터디 시작시간", example = "12:00:00")
    private LocalTime stTime;

    @Schema(description = "스터디 종료시간", example = "15:00:00")
    private LocalTime endTime;

    @Schema(description = "스터디 상태", example = "WAITING")
    private MeetingStatus status;

    public static MeetingResponse from(Meeting meeting) {
        return new MeetingResponse(meeting.getMeetingNo(), meeting.getStudyDate(), meeting.getStTime(), meeting.getEndTime(), meeting.getStatus());
    }
}
