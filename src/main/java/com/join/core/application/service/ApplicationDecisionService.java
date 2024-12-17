package com.join.core.application.service;

import com.join.core.application.domain.Application;
import com.join.core.application.repository.ApplicationRepository;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.constant.StudyRole;
import com.join.core.enrollment.dto.request.EnrollmentCreateRequest;
import com.join.core.enrollment.service.EnrollmentService;
import com.join.core.study.domain.Study;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplicationDecisionService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationStore applicationStore;
    private final EnrollmentService enrollmentService; // EnrollmentService 주입

    @Transactional
    public void acceptApplication(Long applicationId, Long avatarId) {
        Application application = getValidApplication(applicationId, avatarId);
        application.accept();
        applicationStore.store(application);

        EnrollmentCreateRequest enrollmentRequest = new EnrollmentCreateRequest(
                application.getStudy().getId(),
                application.getAvatar().getId(),
                EnrollmentStatus.JOINED,
                StudyRole.MEMBER,
                LocalDateTime.now()
        );

        enrollmentService.createEnrollment(enrollmentRequest, application.getStudy(), application.getAvatar());
    }

    @Transactional
    public void rejectApplication(Long applicationId, Long avatarId) {
        Application application = getValidApplication(applicationId, avatarId);
        application.reject();
        applicationStore.store(application);
    }

    private Application getValidApplication(Long applicationId, Long avatarId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));

        Study study = application.getStudy();

        if (!study.getWriter().getId().equals(avatarId)) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        return application;
    }

}
