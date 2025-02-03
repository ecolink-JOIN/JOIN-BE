package com.join.core.proof.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.meeting.domain.Meeting;
import com.join.core.proof.constant.ProofStatus;
import com.join.core.proof.constant.ProofType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Proof extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProofStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProofType type;

    @NotNull
    private LocalDateTime provenDate;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private ProofPhoto photo;

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
    public Proof(Long id, ProofStatus status, ProofType type, LocalDateTime provenDate, ProofPhoto photo, Meeting meeting, Avatar avatar) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.provenDate = provenDate;
        this.photo = photo;
        this.meeting = meeting;
        this.avatar = avatar;
    }

    public void approve() {
        checkStatus();
        this.status = ProofStatus.APPROVED;
    }

    private void checkStatus() {
        if (!status.isPending()) {
            throw new BadRequestException(ErrorCode.ALREADY_CHECK_PROOF);
        }
    }

    public void reject() {
        checkStatus();
        this.proofStatus = ProofStatus.REJECTED;
    }
}
