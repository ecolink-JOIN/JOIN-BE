package com.join.core.notice.dto.response;

import com.join.core.notice.domain.Notice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class NoticeResponse {

    @Schema(description = "공지사항 ID", example = "1")
    private Long noticeId;

    @Schema(description = "공지사항 작성일", example = "2024.08.15")
    private LocalDate date;

    @Schema(description = "공지사항 제목", example = "약관 변경 안내")
    private String title;

    @Schema(description = "공지사항 내용", example = "약관 변경됩니다.")
    private String content;

    public NoticeResponse(Notice notice) {
        this.noticeId = notice.getId();
        this.date = notice.getDate();
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }

}
