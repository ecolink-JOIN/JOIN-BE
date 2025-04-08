package com.join.core.notification.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.repository.AvatarRepository;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.LeaderForbiddenException;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.notification.domain.Notification;
import com.join.core.notification.domain.NotificationTarget;
import com.join.core.notification.dto.request.NotificationRequest;
import com.join.core.notification.dto.response.NotificationResponse;
import com.join.core.study.domain.Study;
import com.join.core.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationStore notificationStore;
    private final StudyRepository studyRepository;
    private final AvatarRepository avatarRepository;
    private final NotificationReader notificationReader;
    private final EnrollmentReader enrollmentReader;

    @Transactional
    public void postNotice(String studyToken, NotificationRequest request, Long avatarId) {
        Study study = studyRepository.findByStudyToken(studyToken)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STUDY_NOT_FOUND));

        Avatar writer = avatarRepository.findById(avatarId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_FOUND));

        if (!study.getWriter().getId().equals(writer.getId())) {
            throw new LeaderForbiddenException(ErrorCode.NOT_LEADER_OF_STUDY);
        }

        Notification notification = new Notification(
                request.getContent(),
                study,
                writer
        );

        notificationStore.store(notification);

        List<Avatar> joinedAvatars = enrollmentReader.getJoinedAvatarsByStudyId(study.getId());

        List<NotificationTarget> targets = joinedAvatars.stream()
                .map(avatar -> new NotificationTarget(notification, avatar))
                .toList();

        notificationStore.storeTargets(targets);
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> getNotificationsByAvatar(Long avatarId) {
        return notificationReader.readAllByAvatar(avatarId);
    }

}
