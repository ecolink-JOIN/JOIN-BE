package com.join.core.bookmark.repository;

import com.join.core.bookmark.domain.Bookmark;
import com.join.core.bookmark.domain.BookmarkStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookmarkStoreImpl implements BookmarkStore {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public void store(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }

}
