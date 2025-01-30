package com.join.core.enrollment.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByAvatarIdAndStudyIdAndStatus(Long avatarId, Long studyId, EnrollmentStatus status);

    boolean existsByAvatarIdAndStudyIdAndStatusNot(Long avatarId, Long studyId, EnrollmentStatus enrollmentStatus);

}
