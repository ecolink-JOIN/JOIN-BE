package com.join.core.application.service;

import com.join.core.application.domain.Application;
import com.join.core.application.dto.request.ApplicationCreateRequest;
import com.join.core.avatar.domain.Avatar;
import com.join.core.application.repository.ApplicationRepository;
import com.join.core.common.exception.impl.DuplicationApplyException;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import com.join.core.avatar.domain.AvatarReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.join.core.common.exception.ErrorCode.DUPLICATE_APPLICATION;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationStore applicationStore;
    private final StudyReader studyReader;
    private final AvatarReader avatarReader;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public void apply(Long avatarId, ApplicationCreateRequest request) {
        Study study = studyReader.getStudyByToken(request.getStudyToken());
        Avatar avatar = avatarReader.getAvatarById(avatarId);

        boolean isAlreadyApplied = applicationRepository.existsByStudyAndAvatar(study, avatar);
        if (isAlreadyApplied) {
            throw new DuplicationApplyException(DUPLICATE_APPLICATION);
        }

        Application application = new Application(study, avatar, request.getAppDate(), request.getIntroduction());
        applicationStore.store(application);
    }

}
