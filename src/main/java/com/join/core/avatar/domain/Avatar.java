package com.join.core.avatar.domain;

import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.file.domain.SinglePhotoContainer;
import com.join.core.user.domain.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Avatar extends BaseTimeEntity implements SinglePhotoContainer<ProfilePhoto> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String avatarToken;

	@Column(unique = true)
	@NotNull
	@Size(min = 2, max = 7)
	private String nickname;

	private String preferenceId;

	@NotNull
	private int totalRating;

	@NotNull
	private int ratingCnt;

	@NotNull
	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "photo_id")
	private ProfilePhoto photo;

	@Override
	public void changePhoto(ProfilePhoto singlePhoto) {
		this.photo = singlePhoto;
	}

}
