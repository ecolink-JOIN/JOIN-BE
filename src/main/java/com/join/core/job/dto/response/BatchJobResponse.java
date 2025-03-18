package com.join.core.job.dto.response;

import com.join.core.common.constant.DayType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class BatchJobResponse {

    @Schema(description = "알림 내용", example = "내일은 스터디 모임일입니다.")
    private String content;

    @Schema(description = "요일", example = "MON")
    private DayType day;

    @Schema(description = "발송 시간", example = "18:30")
    private LocalTime time;

}