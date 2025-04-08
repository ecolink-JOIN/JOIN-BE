package com.join.core.enrollment.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByAvatarIdAndStudyIdAndStatus(Long avatarId, Long studyId, EnrollmentStatus status);
    Optional<Enrollment> findByAvatarIdAndStudyId(Long avatarId, Long studyId);
    boolean existsByAvatarIdAndStudyStudyTokenAndStatusNot(Long avatarId, String studyToken, EnrollmentStatus enrollmentStatus);
    List<Enrollment> findEnrollmentByStudyIdAndStatus(Long studyId, EnrollmentStatus status);;
    List<Enrollment> findByStudyId(Long studyId);

}
