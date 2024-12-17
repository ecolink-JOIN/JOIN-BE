package com.join.core.enrollment.service;

import com.join.core.enrollment.domain.Enrollment;
import com.join.core.enrollment.dto.request.EnrollmentCreateRequest;
import com.join.core.enrollment.repository.EnrollmentRepository;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public void createEnrollment(EnrollmentCreateRequest request, Study study, Avatar avatar) {
        Enrollment enrollment = new Enrollment(
                study,
                avatar,
                request.getStatus(),
                request.getEnrolledDate(),
                null,
                null,
                request.getRole()
        );
        enrollmentRepository.save(enrollment);
    }

}
