package com.join.core.history.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.bookmark.service.BookmarkReader;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.history.dto.response.ViewStudyReadResponse;
import com.join.core.history.mapper.ViewHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ViewHistoryReadService {

    private final ViewHistoryReader viewHistoryReader;
    private final BookmarkReader bookmarkReader;
    private final EnrollmentReader enrollmentReader;
    private final AvatarReader avatarReader;
    private final ViewHistoryMapper viewHistoryMapper;

    @Transactional(readOnly = true)
    public Page<ViewStudyReadResponse> getViewStudyByAvatarId(Long avatarId, Pageable pageable) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);
        return viewHistoryReader.getStudyByAvatarId(avatarId, pageable)
                .map(study -> {
                    boolean isBookmark = bookmarkReader.isBookmark(study, avatar);
                    double averageRating = enrollmentReader.getAverageByStudyId(study.getId());
                    Avatar leader = enrollmentReader.getLeaderByStudyId(study.getId());
                    return viewHistoryMapper.toViewStudyReadResponse(study, leader, averageRating, isBookmark);
                });
    }
}
