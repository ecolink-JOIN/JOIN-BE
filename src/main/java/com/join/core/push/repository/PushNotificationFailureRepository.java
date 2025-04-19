package com.join.core.push.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.join.core.push.domain.PushNotificationFailure;

public interface PushNotificationFailureRepository extends JpaRepository<PushNotificationFailure, Long> {
}
