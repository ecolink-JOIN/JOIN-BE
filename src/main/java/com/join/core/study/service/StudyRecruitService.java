package com.join.core.study.service;

import com.join.core.address.domain.Address;
import com.join.core.address.service.AddressReader;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.category.domain.Category;
import com.join.core.category.service.CategoryReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.schedule.domain.StudySchedule;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.request.StudyReRecruitRequest;
import com.join.core.study.dto.request.StudyRecruitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyRecruitService {

    private final StudyStore studyStore;
    private final AvatarReader avatarReader;
    private final AddressReader addressReader;
    private final CategoryReader categoryReader;
    private final StudyReader studyReader;

    @Transactional
    public void createStudy(Long avatarId, StudyRecruitRequest recruitRequest) {
        Avatar writer = avatarReader.getAvatarById(avatarId);
        Address address = addressReader.getAddressByLocation(recruitRequest.getProvince(), recruitRequest.getCity());
        Category category = categoryReader.getCategoryByName(recruitRequest.getCategoryName());

        Study study = new Study(recruitRequest, writer, address, category);

        if (recruitRequest.isRegular() && recruitRequest.getSchedules() != null) {
            List<StudySchedule> studySchedules = recruitRequest.getSchedules().stream()
                    .map(scheduleRequest -> new StudySchedule(scheduleRequest.getWeekOfDay(), scheduleRequest.getStTime(), scheduleRequest.getEndTime()))
                    .toList();
            study.addSchedules(studySchedules);
        }

        studyStore.store(study);
    }

    @Transactional
    public void reRecruitStudy(Long avatarId, String studyToken, StudyReRecruitRequest reRecruitRequest) {
        Avatar writer = avatarReader.getAvatarById(avatarId);
        Study study = studyReader.getStudyByToken(studyToken);

        if (!study.getWriter().getId().equals(avatarId)) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        study.updateRecruitDetails(reRecruitRequest);

        studyStore.store(study);
    }

}
