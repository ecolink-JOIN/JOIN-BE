package com.join.core.application.service;

import com.join.core.application.domain.Application;
import com.join.core.application.dto.request.ApplicationCreateRequest;
import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;
import com.join.core.study.service.StudyReader;
import com.join.core.auth.service.AvatarReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationStore applicationStore;
    private final StudyReader studyReader;
    private final AvatarReader avatarReader;

    @Transactional
    public void apply(String avatarToken, ApplicationCreateRequest request) {
        Study study = studyReader.getStudyByToken(request.getStudyToken());
        Avatar avatar = avatarReader.getAvatarByToken(avatarToken);

        Application application = new Application(study, avatar, request.getAppDate(), request.getIntroduction());
        applicationStore.store(application);
    }

}
