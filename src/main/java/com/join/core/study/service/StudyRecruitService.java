package com.join.core.study.service;

import com.join.core.address.domain.Address;
import com.join.core.address.service.AddressReader;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.category.domain.Category;
import com.join.core.category.service.CategoryReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.schedule.domain.StudySchedule;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.request.StudyReRecruitRequest;
import com.join.core.study.dto.request.StudyRecruitRequest;
import com.join.core.enrollment.service.EnrollmentService;
import com.join.core.enrollment.dto.request.EnrollmentCreateRequest;
import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.constant.StudyRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.join.core.common.exception.ErrorCode.INVALID_PARAMETER;

@Service
@RequiredArgsConstructor
public class StudyRecruitService {

    private final StudyStore studyStore;
    private final AvatarReader avatarReader;
    private final AddressReader addressReader;
    private final CategoryReader categoryReader;
    private final StudyReader studyReader;
    private final EnrollmentService enrollmentService;

    @Transactional
    public void createStudy(Long avatarId, StudyRecruitRequest recruitRequest) {
        LocalDate now = LocalDate.now();

        if (recruitRequest.getStDate().isBefore(now) || recruitRequest.getRecruitEndDate().isBefore(now)) {
            throw new InvalidParamException(INVALID_PARAMETER, "스터디 시작일과 모집 종료일은 현재 이후여야 합니다.");
        }
        if (recruitRequest.getStDate().isAfter(recruitRequest.getEndDate())) {
            throw new InvalidParamException(INVALID_PARAMETER, "시작일보다 종료일이 이후여야 합니다.");
        }

        Address address = null;
        if (recruitRequest.getForm() == StudyForm.OFFLINE) {
            if (recruitRequest.getProvince() == null || recruitRequest.getCity() == null) {
                throw new BadRequestException(ErrorCode.ADDRESS_INPUT_REQUIRED);
            }
            address = addressReader.getAddressByLocation(recruitRequest.getProvince(), recruitRequest.getCity());
        }

        Avatar writer = avatarReader.getAvatarById(avatarId);
        Category category = categoryReader.getCategoryByName(recruitRequest.getCategoryName());

        Study study = new Study(recruitRequest, writer, address, category);

        if (recruitRequest.isRegular() && recruitRequest.getSchedules() != null) {
            List<StudySchedule> studySchedules = recruitRequest.getSchedules().stream()
                    .map(scheduleRequest -> new StudySchedule(scheduleRequest.getWeekOfDay(), scheduleRequest.getStTime(), scheduleRequest.getEndTime()))
                    .toList();
            study.addSchedules(studySchedules);
        }

        studyStore.store(study);

        EnrollmentCreateRequest enrollmentRequest = new EnrollmentCreateRequest(
                study.getId(),
                writer.getId(),
                EnrollmentStatus.JOINED,
                StudyRole.LEADER,
                LocalDateTime.now()
        );
        enrollmentService.createEnrollment(enrollmentRequest, study, writer);
    }

    @Transactional
    public void reRecruitStudy(Long avatarId, String studyToken, StudyReRecruitRequest reRecruitRequest) {
        Avatar writer = avatarReader.getAvatarById(avatarId);
        Study study = studyReader.getStudyByToken(studyToken);

        if (!study.getWriter().getId().equals(avatarId)) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }
        if (reRecruitRequest.getRecruitEndDate().isBefore(LocalDate.now())) {
            throw new InvalidParamException(INVALID_PARAMETER, "모집 종료일은 현재 이후여야 합니다.");
        }
        if (study.getStDate().isAfter(reRecruitRequest.getRecruitEndDate())) {
            throw new InvalidParamException(INVALID_PARAMETER, "시작일보다 종료일이 이후여야 합니다.");
        }

        study.updateRecruitDetails(reRecruitRequest);
        studyStore.store(study);
    }

}
