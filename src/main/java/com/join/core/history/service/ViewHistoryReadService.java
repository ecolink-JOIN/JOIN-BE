package com.join.core.history.service;

import com.join.core.history.dto.response.ViewStudyReadResponse;
import com.join.core.history.mapper.ViewHistoryMapper;
import com.join.core.history.repository.ViewHistoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ViewHistoryReadService {

    private final ViewHistoryReader viewHistoryReader;
    private final ViewHistoryMapper viewHistoryMapper;

    @Transactional(readOnly = true)
    public List<ViewStudyReadResponse> getViewStudyByAvatarId(Long avatarId) {
        return viewHistoryReader.getStudyByAvatarId(avatarId).stream()
                .map(study -> viewHistoryMapper.toViewStudyReadResponse(study, 0, true))
                .toList();
    }
}
