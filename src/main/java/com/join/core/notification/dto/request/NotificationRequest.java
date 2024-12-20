package com.join.core.notification.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationRequest {
    @Schema(description = "스터디 공지 내용", example = "다음 주까지 숙제 해오세요.")
    private String content;

}
