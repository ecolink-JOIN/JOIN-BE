package com.join.core.notification.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.repository.AvatarRepository;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.LeaderForbiddenException;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.notification.domain.Notification;
import com.join.core.notification.dto.request.NotificationRequest;
import com.join.core.study.domain.Study;
import com.join.core.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationStore notificationStore;
    private final StudyRepository studyRepository;
    private final AvatarRepository avatarRepository;

    @Transactional
    public void postNotice(Long studyId, NotificationRequest request, Long avatarId) {
        Study study = studyRepository.findById(studyId)
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
    }

}
