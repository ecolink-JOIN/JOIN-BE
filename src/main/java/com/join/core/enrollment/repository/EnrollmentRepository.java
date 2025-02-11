package com.join.core.enrollment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.domain.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByAvatarIdAndStudyIdAndStatus(Long avatarId, Long studyId, EnrollmentStatus status);
    Optional<Enrollment> findByAvatarIdAndStudyId(Long avatarId, Long studyId);
    boolean existsByAvatarIdAndStudyIdAndStatusNot(Long avatarId, Long studyId, EnrollmentStatus enrollmentStatus);
    List<Enrollment> findByStudyId(Long studyId);

}
