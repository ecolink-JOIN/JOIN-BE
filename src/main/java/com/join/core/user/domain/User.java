package com.join.core.user.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.user.constant.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private LocalDateTime singUpDate;

    @NotNull
    private String status;

    @NotNull
    private String platform;

    @OneToMany(mappedBy = "user")
    private List<Avatar> avatars = new ArrayList<>();

}