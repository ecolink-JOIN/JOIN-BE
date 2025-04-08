package com.join.core.avatar.service;

import com.join.core.attendance.service.AttendanceRateService;
import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.avatar.dto.response.AvatarParticipationRateResponse;
import com.join.core.proof.service.ProofRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AvatarReadService {

    private final AvatarReader avatarReader;
    private final AttendanceRateService attendanceRateService;
    private final ProofRateService proofRateService;

    @Transactional(readOnly = true)
    public AvatarParticipationRateResponse getAttendanceAndProofRate(String avatarToken) {
        Avatar avatar = avatarReader.getAvatarByAvatarToken(avatarToken);

        double averageAttendanceRate = attendanceRateService.calculateIndividualAttendanceRate(avatar.getId());
        double averageProofRate = proofRateService.calculateIndividualProofRate(avatar.getId());

        return AvatarParticipationRateResponse.of(avatar, averageAttendanceRate, averageProofRate);
    }
}
