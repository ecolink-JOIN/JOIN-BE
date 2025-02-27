package com.join.core.job.dto.request;

import com.join.core.common.constant.DayType;
import com.join.core.job.domain.BatchJob;
import com.join.core.study.domain.Study;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class BatchJobRequest {

    @Schema(description = "알림 내용", example = "내일은 스터디 모임일입니다.")
    @NotNull
    @Size(min = 10, max = 100)
    private String content;

    @Schema(description = "요일", example = "MON")
    @NotNull
    private DayType day;

    @Schema(description = "발송 시간", example = "18:30")
    @NotNull
    private LocalTime time;

    @Schema(description = "스터디 토큰", example = "std_abc")
    @NotNull
    private String studyToken;

    public BatchJob toEntity(Study study) {
        return new BatchJob(content, day, time, study);
    }

}
