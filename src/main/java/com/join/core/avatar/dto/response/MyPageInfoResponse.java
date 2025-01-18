package com.join.core.avatar.dto.response;

import com.join.core.avatar.domain.Avatar;
import com.join.core.file.domain.ImageFile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageInfoResponse {

    @Schema(description = "아바타 토큰", example = "token-example")
    private String avatarToken;
    @Schema(description = "닉네임", example = "닉네임 예시")
    private String nickname;
    @Schema(description = "사용자 프로필 이미지", example = "image url")
    private ImageFile image;

    //사용자 성실도
    @Schema(description = "평균 출석율", example = "100.0")
    private double averageAttendanceRate;
    @Schema(description = "평균 인증율", example = "97.0")
    private double averageProofRate;
    @Schema(description = "평점(공식: avatar rating / cnt / 3)", example = "5.0")
    private double averageRating; // avatar rating / cnt / 3

    public static MyPageInfoResponse of(Avatar avatar, double averageAttendanceRate, double averageProofRate) {
        return new MyPageInfoResponse(
                avatar.getAvatarToken(),
                avatar.getNickname(),
                avatar.getPhoto().getFile(),
                averageAttendanceRate,
                averageProofRate,
                avatar.getAverageEvaluation()
        );
    }
}
