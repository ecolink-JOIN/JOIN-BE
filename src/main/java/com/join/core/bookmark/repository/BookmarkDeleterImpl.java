package com.join.core.bookmark.repository;

import com.join.core.bookmark.domain.Bookmark;
import com.join.core.bookmark.domain.BookmarkDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookmarkDeleterImpl implements BookmarkDeleter {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public void deleteBookmark(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
    }

}
