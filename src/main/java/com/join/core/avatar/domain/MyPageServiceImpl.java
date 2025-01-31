package com.join.core.avatar.domain;

import com.join.core.attendance.service.AttendanceRateService;
import com.join.core.avatar.dto.response.MyPageInfoResponse;
import com.join.core.proof.service.ProofRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageServiceImpl implements MyPageService {

    private final AvatarReader avatarReader;
    private final AttendanceRateService attendanceRateService;
    private final ProofRateService proofRateService;

    @Transactional(readOnly = true)
    @Override
    public MyPageInfoResponse getMyPageInfo(Long avatarId) {
        Avatar avatar = avatarReader.getAvatarById(avatarId);

        double averageAttendanceRate = attendanceRateService.calculateIndividualAttendanceRate(avatarId);

        double averageProofRate = proofRateService.calculateIndividualProofRate(avatarId);

        return MyPageInfoResponse.of(avatar, averageAttendanceRate, averageProofRate);
    }
}
