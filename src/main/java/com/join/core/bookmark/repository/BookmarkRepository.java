package com.join.core.bookmark.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.bookmark.domain.Bookmark;
import com.join.core.study.domain.Study;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Page<Bookmark> findAllByAvatar(Pageable pageable, Avatar avatar);
    boolean existsByAvatarAndStudy(Avatar avatar, Study study);
    @Query("SELECT b FROM Bookmark b WHERE b.avatar.id = :avatarId AND b.study.studyToken = :studyToken")
    Optional<Bookmark> findBookmarkByAvatarIdAndStudyToken(@Param("avatarId") Long avatarId, @Param("studyToken") String studyToken);
    @Query("SELECT COUNT(b) > 0 FROM Bookmark b WHERE b.avatar.id = :avatarId AND b.study.id = :studyId")
    boolean existsByAvatarIdAndStudyId(@Param("avatarId") Long avatarId, @Param("studyId") Long studyId);

}
