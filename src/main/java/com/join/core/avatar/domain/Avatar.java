package com.join.core.avatar.domain;

import static com.join.core.common.exception.ErrorCode.*;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.join.core.auth.domain.User;
import com.join.core.common.domain.BaseTimeEntity;
import com.join.core.common.exception.impl.InvalidParamException;
import com.join.core.common.util.TokenGenerator;
import com.join.core.file.domain.SinglePhotoContainer;

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

	private static final String AVATAR_PREFIX = "avt_";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String avatarToken;

	@Column(unique = true)
	@Nullable
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

	public Avatar(User user) {
		if (user == null)
			throw new InvalidParamException(INVALID_PARAMETER, "Avatar.user");

		this.avatarToken = TokenGenerator.randomCharacterWithPrefix(AVATAR_PREFIX);
		this.nickname = null;
		this.totalRating = 0;
		this.ratingCnt = 0;
		this.user = user;
	}

	public boolean isNicknameSet() {
		return StringUtils.isNotEmpty(nickname);
	}

	public void changeNickname(String nickname) {
		if(StringUtils.isBlank(nickname))
			throw new InvalidParamException(INVALID_PARAMETER, "Avatar.nickname");
		this.nickname = nickname;
	}
}
