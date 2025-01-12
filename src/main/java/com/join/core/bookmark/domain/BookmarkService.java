package com.join.core.bookmark.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.common.exception.impl.EntityAlreadyExistsException;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookmarkService {

    private final BookmarkReader bookmarkReader;
    private final BookmarkStore bookmarkStore;
    private final AvatarReader avatarReader;
    private final StudyReader studyReader;
    private final BookmarkDeleter bookmarkDeleter;

    private boolean existsBookmark(Long avatarId, Long studyId) {
        return bookmarkReader.findBookmarkByAvatarAndStudy(avatarId, studyId).isPresent();
    }

    @Transactional
    public Bookmark addBookmark(Long studyId, Long avatarId) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);
        Study study = studyReader.getStudyById(studyId);

        if (existsBookmark(avatarId, studyId)) {
            throw new EntityAlreadyExistsException(ErrorCode.BOOKMARK_ALREADY_EXISTS);
        }

        Bookmark bookmark = new Bookmark(study, avatar);
        bookmarkStore.store(bookmark);

        study.addBookmarkCount();

        return bookmark;
    }

    public Bookmark getBookmark(Long storeId, Long avatarId) {
        return bookmarkReader.findBookmarkByAvatarAndStudy(avatarId, storeId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.BOOKMARK_NOT_FOUND));
    }

    @Transactional
    public void deleteBookmark(Long storeId, Long avatarId) {
        Bookmark bookmark = getBookmark(storeId, avatarId);
        Study study = bookmark.getStudy();

        bookmarkDeleter.deleteBookmark(bookmark);

        study.deleteBookmarkCount();
    }

}

