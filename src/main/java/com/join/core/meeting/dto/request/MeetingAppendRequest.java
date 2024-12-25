package com.join.core.meeting.dto.request;

import com.join.core.meeting.domain.Meeting;
import com.join.core.study.domain.Study;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class MeetingAppendRequest {
    @Schema(description = "스터디 회차 날짜", example = "2024-09-01")
    @NotNull
    private LocalDate studyDate;

    @Schema(description = "스터디 회차 시작 시간", example = "10:00")
    @NotNull
    private LocalTime stTime;

    @Schema(description = "스터디 회차 종료 시간", example = "14:00")
    @NotNull
    private LocalTime endTime;

    public Meeting toEntity(Study study) {
        return new Meeting(studyDate, stTime, endTime, study);
    }
}
