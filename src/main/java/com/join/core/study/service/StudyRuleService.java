package com.join.core.study.service;

import com.join.core.address.domain.Address;
import com.join.core.address.service.AddressReader;
import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.LeaderForbiddenException;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.enrollment.service.EnrollmentReader;
import com.join.core.schedule.dto.request.StudyScheduleRequest;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.request.UpdateStudyRuleRequest;
import com.join.core.study.dto.response.StudyRuleResponse;
import com.join.core.study.mapper.StudyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyRuleService {

    private final AddressReader addressReader;
    private final EnrollmentReader enrollmentReader;
    private final StudyReader studyReader;
    private final StudyMapper studyMapper;
    private final StudyStore studyStore;

    @Transactional(readOnly = true)
    public StudyRuleResponse getRules(String studyToken) {
        Study study = studyReader.getStudyByToken(studyToken);
        return StudyRuleResponse.of(study, studyMapper.toStudyScheduleResponse(study), studyMapper.toFineReasonAmountsDto(study));
    }

    @Transactional
    public void updateRules(Long avatarId, String studyToken, UpdateStudyRuleRequest request) {
        Study study = studyReader.getStudyByToken(studyToken);
        Avatar leader = enrollmentReader.getLeaderByStudyId(study.getId());

        if (!leader.isSameAvatar(avatarId)) {
            throw new LeaderForbiddenException(ErrorCode.LEADER_ONLY_ACCESS);
        }

        if (request.form().form() == StudyForm.OFFLINE) {
            if (request.form().province() == null || request.form().city() == null) {
                throw new BadRequestException(ErrorCode.ADDRESS_INPUT_REQUIRED);
            }
            Address newAddress = addressReader.getAddressByLocation(request.form().province(), request.form().city());
            study.updateFormToOffline(newAddress);
        } else {
            study.updateFormToOnline();
        }

        study.updateRuleExp(request.ruleExp());

        study.updateRules(request.rules());

        study.updateStudySchedule(request.startDate(), request.endDate(), request.schedules().stream().map(StudyScheduleRequest::toStudySchedule).toList());

        if (Boolean.TRUE.equals(request.fine().isFineEnabled())) {
            if (!request.rules().contains("FINE")) {
                throw new BadRequestException(ErrorCode.FINE_INPUT_REQUIRED);
            }

            if (request.fine().tardiness() == null
                    || request.fine().absence() == null
                    || request.fine().nonProof() == null) {
                throw new BadRequestException(ErrorCode.FINE_INPUT_REQUIRED);
            }
            study.addFineToRules(request.fine().tardiness(), request.fine().absence(), request.fine().nonProof());
        } else {
            study.disableFine();
        }

        studyStore.store(study);
    }
}
