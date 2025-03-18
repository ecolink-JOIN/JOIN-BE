package com.join.core.enrollment.constant;

public enum StudyRole {
    LEADER, MEMBER;

    public boolean isLeader() {
        return this == LEADER;
    }
}
