package com.join.core.enrollment.dto.request;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.constant.StudyRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentCreateRequest {

    private Long studyId;
    private Long avatarId;
    private EnrollmentStatus status;
    private StudyRole role;
    private LocalDateTime enrolledDate;
}