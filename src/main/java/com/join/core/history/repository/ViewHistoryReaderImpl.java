package com.join.core.history.repository;

import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ViewHistoryReaderImpl implements ViewHistoryReader {

    private final ViewHistoryRepository viewHistoryRepository;

    @Override
    public List<Study> getStudyByAvatarId(Long avatarId) {
        return viewHistoryRepository.findDistinctStudiesByAvatarId(avatarId);
    }
}
