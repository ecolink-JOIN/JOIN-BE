package com.join.core.bookmark.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.bookmark.dto.response.BookmarkStudyReadResponse;
import com.join.core.bookmark.mapper.BookmarkMapper;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookmarkReadService {

    private final BookmarkReader bookmarkReader;
    private final AvatarReader avatarReader;
    private final EnrollmentReader enrollmentReader;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)
    public Page<BookmarkStudyReadResponse> getBookmarkStudy(Long avatarId, Pageable pageable) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);
        return bookmarkReader.getBookmarksByAvatar(pageable, avatar)
                .map(bookmark -> {
                    Study study = bookmark.getStudy();
                    boolean isBookmark = bookmarkReader.isBookmark(study, avatar);
                    double averageRating = enrollmentReader.getAverageByStudyId(study.getId());
                    Avatar leader = enrollmentReader.getLeaderByStudyId(study.getId());
                    return bookmarkMapper.toBookmarkStudyReadResponse(study, leader, averageRating, isBookmark);
                });
    }
}
