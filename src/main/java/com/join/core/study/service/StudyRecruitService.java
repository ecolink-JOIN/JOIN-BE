package com.join.core.study.service;

import com.join.core.auth.service.AvatarReader;
import com.join.core.address.service.AddressReader;
import com.join.core.category.service.CategoryReader;
import com.join.core.avatar.domain.Avatar;
import com.join.core.address.domain.Address;
import com.join.core.category.domain.Category;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.request.StudyDescriptionRequest;
import com.join.core.study.dto.request.StudyInfoRequest;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidParamException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudyRecruitService {

    private final StudyStore studyStore;
    private final AvatarReader avatarReader;
    private final AddressReader addressReader;
    private final CategoryReader categoryReader;

    private final Map<String, StudyInfoRequest> tempStudyInfoStore = new HashMap<>();

    public void saveStudyInfo(StudyInfoRequest infoRequest, String avatarToken) {
        tempStudyInfoStore.put(avatarToken, infoRequest);
    }

    @Transactional
    public void createStudy(String avatarToken, StudyDescriptionRequest descriptionRequest) {
        StudyInfoRequest infoRequest = tempStudyInfoStore.get(avatarToken);
        if (infoRequest == null) {
            throw new InvalidParamException(ErrorCode.INVALID_TYPE_VALUE, "스터디 기본 정보를 작성해 주세요.");
        }

        Avatar writer = avatarReader.getAvatarByToken(avatarToken);
        Address address = addressReader.getAddressByLocation(infoRequest.getProvince(), infoRequest.getCity());
        Category category = categoryReader.getCategoryByName(infoRequest.getCategoryName());

        Study study = new Study(infoRequest, writer, address, category);
        study.addStudyDescription(descriptionRequest);
        studyStore.store(study);

        tempStudyInfoStore.remove(avatarToken);
    }

}
