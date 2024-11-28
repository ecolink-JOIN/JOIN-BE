package com.join.core.history.service;

import com.join.core.application.domain.Application;
import com.join.core.application.repository.ApplicationReader;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.bookmark.repository.BookmarkReader;
import com.join.core.history.dto.request.PageParameterRequest;
import com.join.core.history.dto.response.ViewStudyReadResponse;
import com.join.core.history.mapper.ViewHistoryMapper;
import com.join.core.history.repository.ViewHistoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ViewHistoryReadService {

    private final ViewHistoryReader viewHistoryReader;
    private final BookmarkReader bookmarkReader;
    private final ApplicationReader applicationReader;
    private final AvatarReader avatarReader;
    private final ViewHistoryMapper viewHistoryMapper;

    @Transactional(readOnly = true)
    public Page<ViewStudyReadResponse> getViewStudyByAvatarId(Long avatarId, PageParameterRequest pageParameterRequest) {
        Pageable pageable = PageRequest.of(pageParameterRequest.page() - 1, pageParameterRequest.size());
        Avatar avatar = avatarReader.getAvatarById(avatarId);
        return viewHistoryReader.getStudyByAvatarId(avatarId, pageable)
                .map(study -> {
                    boolean isBookmark = bookmarkReader.isBookmark(study, avatar);
                    double averageRating = getAverageRating(study.getId());
                    return viewHistoryMapper.toViewStudyReadResponse(study, averageRating, isBookmark);
                });
    }

    private double getAverageRating(Long studyId) {
        List<Application> applications = applicationReader.getApproveApplications(studyId);
        return applications.stream()
                .mapToDouble(application -> application.getAvatar().getTotalRating())
                .sum() / applications.size();
    }
}
