package com.join.core.bookmark.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.bookmark.dto.response.BookmarkStudyReadResponse;
import com.join.core.bookmark.mapper.BookmarkMapper;
import com.join.core.bookmark.repository.BookmarkReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkReadService {

    private final BookmarkReader bookmarkReader;
    private final AvatarReader avatarReader;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)
    public List<BookmarkStudyReadResponse> getBookmarkStudy(Long avatarId) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);
        return bookmarkReader.getBookmarksByAvatar(avatar).stream()
                .map(bookmark -> bookmarkMapper.toBookmarkStudyReadResponse(bookmark.getStudy(), 0))
                .toList();
    }
}
