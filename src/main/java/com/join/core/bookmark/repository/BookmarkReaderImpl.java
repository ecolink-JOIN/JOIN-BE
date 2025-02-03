package com.join.core.bookmark.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.bookmark.domain.Bookmark;
import com.join.core.bookmark.domain.BookmarkReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookmarkReaderImpl implements BookmarkReader {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public Page<Bookmark> getBookmarksByAvatar(Pageable pageable, Avatar avatar) {
        return bookmarkRepository.findAllByAvatar(pageable, avatar);
    }

    @Override
    public boolean isBookmark(Study study, Avatar avatar) {
        return bookmarkRepository.existsByAvatarAndStudy(avatar, study);
    }

    @Override
    public Bookmark findBookmarkByAvatarAndStudy(Long avatarId, Long studyId) {
        return bookmarkRepository.findBookmarkByAvatarIdAndStudyId(avatarId, studyId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.BOOKMARK_NOT_FOUND));
    }

    @Override
    public boolean existsByAvatarAndStudy(Long avatarId, Long studyId) {
        return bookmarkRepository.existsByAvatarIdAndStudyId(avatarId, studyId);
    }

}
