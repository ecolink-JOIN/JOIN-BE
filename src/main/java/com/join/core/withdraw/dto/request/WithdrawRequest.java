package com.join.core.withdraw.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.join.core.withdraw.constant.WithdrawType;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import com.join.core.withdraw.domain.Withdraw;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WithdrawRequest {

    @NotNull
    @Schema(description = "탈퇴 유형", example = "APPROVAL_REQUIRED, SELF_WITHDRAW")
    private WithdrawType withdrawType;

    @Size(min = 10, max = 150)
    @Schema(description = "탈퇴 사유", example = "스터디 활동을 원하지 않습니다.")
    private String reason;

    public Withdraw toEntity(Study study, Avatar avatar) {
        return new Withdraw(
                study,
                avatar,
                this.withdrawType,
                this.reason
        );
    }
}
