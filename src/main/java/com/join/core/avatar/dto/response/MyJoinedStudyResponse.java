package com.join.core.avatar.dto.response;

import com.join.core.study.constant.StudyStatus;
import com.join.core.study.domain.Study;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MyJoinedStudyResponse(
        @Schema(description = "진행중인 스터디 갯수 - COMPLETED 상태를 제외한 스터디 갯수", example = "1")
        Long ongoingStudyCount,
        @Schema(description = "완료한 스터디 갯수", example = "2")
        Long completedStudyCount,
        @Schema(description = "가입한 스터디 목록")
        List<JoinStudyInfoDto> joinStudyInfos
) {
    public record JoinStudyInfoDto(
            @Schema(description = "스터디 이름", example = "직장인 영어 회화 스터디")
            String name,
            @Schema(description = "모집 상태", example = "RECRUITING, READY, ACTIVE, COMPLETED 중 하나(모집중, 모집완료, 활동중, 활동완료)")
            StudyStatus status
    ) {

        public static JoinStudyInfoDto of(String name, StudyStatus status) {
            return new JoinStudyInfoDto(name, status);
        }
    }

    public static MyJoinedStudyResponse of(List<Study> studies) {
        long ongoingStudyCount = studies.stream().filter(study -> !study.getStatus().equals(StudyStatus.COMPLETED)).count();
        long completedStudyCount = studies.stream().filter(study -> study.getStatus().equals(StudyStatus.COMPLETED)).count();
        List<JoinStudyInfoDto> joinStudyInfos = studies.stream().map(study -> JoinStudyInfoDto.of(study.getStudyName(), study.getStatus())).toList();

        return new MyJoinedStudyResponse(
                ongoingStudyCount,
                completedStudyCount,
                joinStudyInfos
        );
    }
}
