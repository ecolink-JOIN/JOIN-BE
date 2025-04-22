package com.join.core.evaluation.dto.request;

import com.join.core.avatar.domain.Avatar;
import com.join.core.evaluation.domain.Evaluation;
import com.join.core.study.domain.Study;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EvaluationRequest {

    public Evaluation toEntity(Study study, Avatar rater, Avatar ratee) {
        return new Evaluation(sincerity, familiarity, effect, ratee, rater, study);
    }

    @NotNull
    @Schema(description = "스터디 토큰", example = "std_abc")
    private String studyToken;

    @NotNull
    @Schema(description = "평가대상자 토큰", example = "avt_abc")
    private String rateeToken;

    @NotNull
    @Min(1)
    @Max(5)
    @Schema(description = "성실도 평가 (1 ~ 5)", example = "4")
    private int sincerity;

    @NotNull
    @Min(1)
    @Max(5)
    @Schema(description = "프로그램 숙지도 평가 (1 ~ 5)", example = "3")
    private int familiarity;

    @NotNull
    @Min(1)
    @Max(5)
    @Schema(description = "학습 분위기 영향 평가 (1 ~ 5)", example = "5")
    private int effect;

}