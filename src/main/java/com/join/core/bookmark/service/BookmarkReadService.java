package com.join.core.bookmark.service;

import com.join.core.application.domain.Application;
import com.join.core.application.repository.ApplicationReader;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.bookmark.dto.request.PageParameterRequest;
import com.join.core.bookmark.dto.response.BookmarkStudyReadResponse;
import com.join.core.bookmark.mapper.BookmarkMapper;
import com.join.core.bookmark.repository.BookmarkReader;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkReadService {

    private final BookmarkReader bookmarkReader;
    private final AvatarReader avatarReader;
    private final ApplicationReader applicationReader;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)
    public Page<BookmarkStudyReadResponse> getBookmarkStudy(Long avatarId, PageParameterRequest pageParameterRequest) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);
        Pageable pageable = PageRequest.of(pageParameterRequest.page() - 1, pageParameterRequest.size());
        return bookmarkReader.getBookmarksByAvatar(pageable, avatar)
                .map(bookmark -> {
                    Study study = bookmark.getStudy();
                    double averageRating = getAverageRating(study.getId());
                    return bookmarkMapper.toBookmarkStudyReadResponse(study, averageRating, bookmarkReader.isBookmark(study, avatar));
                });
    }

    private double getAverageRating(Long studyId) {
        List<Application> applications = applicationReader.getApproveApplications(studyId);
        return applications.stream()
                .mapToDouble(application -> application.getAvatar().getTotalRating())
                .sum() / applications.size();
    }
}
