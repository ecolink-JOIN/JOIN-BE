package com.join.core.withdraw.dto.response;

import com.join.core.avatar.domain.Avatar;
import com.join.core.file.domain.ImageFile;
import com.join.core.withdraw.domain.Withdraw;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithdrawResponse {

    @Schema(description = "탈퇴 요청 ID", example = "1")
    private Long withdrawId;

    @Schema(description = "탈퇴 요청자 닉네임", example = "happy")
    private String nickname;

    @Schema(description = "사용자 프로필 이미지", example = "image url")
    private ImageFile profileImage;

    public static WithdrawResponse from(Withdraw withdraw, Avatar avatar) {
        return new WithdrawResponse(
                withdraw.getId(),
                avatar.getNickname(),
                avatar.getPhoto().getFile()
        );
    }
}
