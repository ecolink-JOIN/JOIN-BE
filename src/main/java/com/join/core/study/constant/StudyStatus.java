package com.join.core.study.constant;

public enum StudyStatus {
    //RECRUITING : 미진행 + 모집O -> 시작 전 모집 중
    //READY : 미진행 + 모집 X -> 시작 전 준비 완료
    //ACTIVE_RECRUITING : 진행중 + 모집O
    //ACTIVE : 진행중 + 모집X
    //COMPLETED : 미진행 + 모집 X -> 활동 종료
    RECRUITING, READY, ACTIVE_RECRUITING, ACTIVE, COMPLETED

}
