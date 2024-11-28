package com.join.core.history.repository;

import com.join.core.history.domain.ViewHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ViewHistoryReaderImpl implements ViewHistoryReader {

    private final ViewHistoryRepository viewHistoryRepository;

    @Override
    public List<ViewHistory> getViewsByAvatarId(Long avatarId) {
        return viewHistoryRepository.findByAvatarId(avatarId);
    }
}
