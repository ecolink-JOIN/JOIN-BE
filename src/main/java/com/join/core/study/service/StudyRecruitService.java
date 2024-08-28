package com.join.core.study.service;

import com.join.core.address.domain.Address;
import com.join.core.address.repository.AddressRepository;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.repository.AvatarRepository;
import com.join.core.category.domain.Category;
import com.join.core.category.repository.CategoryRepository;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.EntityNotFoundException;
import com.join.core.common.exception.impl.InvalidSelectionException;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.request.StudyInfoRequest;
import com.join.core.study.dto.request.StudyDescriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudyRecruitService {

    private final StudyService studyService;
    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;
    private final AvatarRepository avatarRepository;

    private final Map<Long, StudyInfoRequest> tempStudyInfoStore = new HashMap<>();

    public void saveStudyInfo(StudyInfoRequest infoRequest, Long avatarId) {
        tempStudyInfoStore.put(avatarId, infoRequest);
    }

    @Transactional
    public void createStudy(Long avatarId, StudyDescriptionRequest descriptionRequest) {
        StudyInfoRequest infoRequest = tempStudyInfoStore.get(avatarId);
        if (infoRequest == null) {
            throw new IllegalStateException("기본 정보가 누락되었습니다."); // invalidParamException 으로 변경
        }

        Avatar writer = avatarRepository.findById(avatarId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.AVATAR_NOT_FOUND));

        Address address = addressRepository.findByProvinceAndCity(infoRequest.getProvince(), infoRequest.getCity())
                .orElseThrow(() -> new InvalidSelectionException(ErrorCode.ADDRESS_SELECTION_REQUIRED));

        Category category = categoryRepository.findByCategoryName(infoRequest.getCategoryName())
                .orElseThrow(() -> new InvalidSelectionException(ErrorCode.CATEGORY_SELECTION_REQUIRED));

        Study study = new Study(infoRequest, writer, address, category);
        study.addStudyDescription(descriptionRequest);
        studyService.saveStudy(study);

        tempStudyInfoStore.remove(avatarId);
    }

}
