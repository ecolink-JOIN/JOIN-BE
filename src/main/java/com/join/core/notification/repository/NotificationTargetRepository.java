package com.join.core.notification.repository;

import com.join.core.notification.domain.NotificationTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationTargetRepository extends JpaRepository<NotificationTarget, Long> {
    @Query("select nt from NotificationTarget nt join fetch nt.notification where nt.target.id = :avatarId")
    List<NotificationTarget> findWithNotificationByTargetId(@Param("avatarId") Long avatarId);
}