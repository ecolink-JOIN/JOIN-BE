package com.join.core.bookmark.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.bookmark.domain.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkReader {

    Page<Bookmark> getBookmarksByAvatar(Pageable pageable, Avatar avatar);
}
