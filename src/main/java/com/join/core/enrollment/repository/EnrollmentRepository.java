package com.join.core.enrollment.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByAvatarIdAndStudyIdAndStatus(Long avatarId, Long studyId, EnrollmentStatus status);
    Optional<Enrollment> findByAvatarIdAndStudyId(Long avatarId, Long studyId);
}
