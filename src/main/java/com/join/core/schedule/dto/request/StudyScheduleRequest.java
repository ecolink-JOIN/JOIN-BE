package com.join.core.schedule.dto.request;

import com.join.core.common.constant.DayType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class StudyScheduleRequest {

    @Schema(description = "요일", example = "MON")
    @NotNull
    private DayType weekOfDay;

    @Schema(description = "시작 시간", example = "10:00")
    @NotNull
    private LocalTime stTime;

    @Schema(description = "종료 시간", example = "12:00")
    @NotNull
    private LocalTime endTime;

}
