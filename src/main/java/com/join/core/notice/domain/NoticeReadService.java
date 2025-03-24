package com.join.core.notice.domain;

import com.join.core.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeReadService {

    private final NoticeReader noticeReader;

    public List<NoticeResponse> getNotices() {
        List<Notice> notices = noticeReader.findAll();

        return notices.stream()
                .map(NoticeResponse::new)
                .collect(Collectors.toList());
    }
}
