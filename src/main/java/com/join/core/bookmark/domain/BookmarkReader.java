package com.join.core.bookmark.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkReader {

    Page<Bookmark> getBookmarksByAvatar(Pageable pageable, Avatar avatar);
    boolean isBookmark(Study study, Avatar avatar);
    Bookmark findBookmarkByAvatarAndStudy(Long avatarId, Long studyId);
    boolean existsByAvatarAndStudy(Long avatarId, Long studyId);

}
