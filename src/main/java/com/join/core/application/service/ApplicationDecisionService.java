package com.join.core.application.service;

import com.join.core.application.domain.Application;
import com.join.core.application.repository.ApplicationRepository;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.study.domain.Study;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationDecisionService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationStore applicationStore;

    @Transactional
    public void acceptApplication(Long applicationId, Long avatarId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));

        Study study = application.getStudy();

        if (!study.getWriter().getId().equals(avatarId)) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        application.accept();
        applicationStore.store(application);
    }

}
