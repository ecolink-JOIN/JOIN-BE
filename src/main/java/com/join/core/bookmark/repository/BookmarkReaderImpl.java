package com.join.core.bookmark.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.bookmark.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookmarkReaderImpl implements BookmarkReader {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public List<Bookmark> getBookmarksByAvatar(Avatar avatar) {
        return bookmarkRepository.findAllByAvatar(avatar);
    }
}
