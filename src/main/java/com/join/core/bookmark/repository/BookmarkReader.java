package com.join.core.bookmark.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.bookmark.domain.Bookmark;

import java.util.List;

public interface BookmarkReader {

    List<Bookmark> getBookmarksByAvatar(Avatar avatar);
}
