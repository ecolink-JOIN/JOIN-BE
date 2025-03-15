package com.join.core.study.service;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.NoPermissionException;
import com.join.core.study.domain.Study;
import com.join.core.study.dto.request.StudyEndRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StudyEndService {

    private final StudyStore studyStore;
    private final StudyReader studyReader;

    @Transactional
    public void endStudy(String studyToken, StudyEndRequest endRequest, UserPrincipal principal) {
        Study study = studyReader.getStudyByToken(studyToken);
        Avatar writer = study.getWriter();

        if (!writer.getId().equals(principal.getAvatarId())) {
            throw new NoPermissionException(ErrorCode.UNAUTHORIZED_ACCESS);
        }

        LocalDate actualEndDate = endRequest.getActualEndDate();

        study.endStudy(actualEndDate);

        studyStore.store(study);
    }

}
