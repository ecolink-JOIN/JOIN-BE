package com.join.core.bookmark.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.bookmark.domain.Bookmark;
import com.join.core.study.domain.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Page<Bookmark> findAllByAvatar(Pageable pageable, Avatar avatar);
    boolean existsByAvatarAndStudy(Avatar avatar, Study study);
    Optional<Bookmark> findBookmarkByAvatarIdAndStudyId(Long avatarId, Long studyId);

}
