package com.join.core.attendance.domain;

import com.join.core.attendance.constant.AttendanceStatus;
import com.join.core.avatar.domain.Avatar;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.meeting.domain.Meeting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static com.join.core.common.exception.ErrorCode.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Attendance extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id", nullable = false)
    private Avatar avatar;

    @Builder
    public Attendance(AttendanceStatus status, Meeting meeting, Avatar avatar) {
        if (status == null)
            throw new InvalidParamException(INVALID_PARAMETER, "Attendance.status");
        if (meeting == null)
            throw new InvalidParamException(INVALID_PARAMETER, "Attendance.session");
        if (avatar == null)
            throw new InvalidParamException(INVALID_PARAMETER, "Attendance.avatar");
        this.status = status;
        this.meeting = meeting;
        this.avatar = avatar;
    }

    public void updateStatus(AttendanceStatus status) {
        this.status = status;
    }
}
