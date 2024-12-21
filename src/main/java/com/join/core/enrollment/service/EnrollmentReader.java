package com.join.core.enrollment.service;

public interface EnrollmentReader {

    boolean existEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId);
}
