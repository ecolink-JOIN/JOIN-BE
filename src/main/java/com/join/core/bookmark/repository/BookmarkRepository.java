package com.join.core.bookmark.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findAllByAvatar(Avatar avatar);
}
