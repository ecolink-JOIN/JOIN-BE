package com.join.core.application.service;

import com.join.core.application.constant.ApplicationRejectReason;
import com.join.core.application.domain.Application;
import com.join.core.application.repository.ApplicationRepository;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.common.exception.impl.InvalidParamException;
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

import static com.join.core.common.exception.ErrorCode.INVALID_PARAMETER;

@Service
@RequiredArgsConstructor
public class ApplicationDecisionService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationStore applicationStore;
    private final EnrollmentService enrollmentService;

    @Transactional
    public void acceptApplication(Long applicationId, Long avatarId) {
        Application application = getValidApplication(applicationId, avatarId);
        application.accept();
        applicationStore.store(application);

        EnrollmentCreateRequest enrollmentRequest = new EnrollmentCreateRequest(
                application.getStudy().getId(),
                application.getAvatar().getId(),
                EnrollmentStatus.READY_JOIN,
                StudyRole.MEMBER,
                LocalDateTime.now()
        );

        enrollmentService.createEnrollment(enrollmentRequest, application.getStudy(), application.getAvatar());
    }

    @Transactional
    public void rejectApplication(Long applicationId, Long avatarId, ApplicationRejectReason rejectReason, String otherReason) {
        if (rejectReason == ApplicationRejectReason.OTHER && (otherReason == null || otherReason.trim().isEmpty())) {
            throw new InvalidParamException(INVALID_PARAMETER, "기타 선택 시 사유를 입력해야 합니다.");
        }
        Application application = getValidApplication(applicationId, avatarId);
        application.reject(rejectReason, otherReason);
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
