package com.join.core.history.repository;

import com.join.core.history.service.ViewHistoryReader;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ViewHistoryReaderImpl implements ViewHistoryReader {

    private final ViewHistoryRepository viewHistoryRepository;

    @Override
    public Page<Study> getStudyByAvatarId(Long avatarId, Pageable pageable) {
        return viewHistoryRepository.findDistinctStudiesByAvatarId(avatarId, pageable);
    }
}
