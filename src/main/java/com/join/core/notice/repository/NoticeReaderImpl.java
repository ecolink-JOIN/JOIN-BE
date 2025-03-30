package com.join.core.notice.repository;

import com.join.core.common.exception.ErrorCode;
import com.join.core.notice.domain.Notice;
import java.util.List;

import com.join.core.notice.domain.NoticeReader;
import com.join.core.common.exception.impl.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeReaderImpl implements NoticeReader {

    private final NoticeRepository noticeRepository;

    @Override
    public List<Notice> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        if (notices.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.NOTICE_NOT_FOUND);
        }
        return notices;
    }

}
