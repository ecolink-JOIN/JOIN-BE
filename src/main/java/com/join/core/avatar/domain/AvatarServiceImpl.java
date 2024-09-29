package com.join.core.avatar.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.join.core.common.exception.impl.InvalidNicknameException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AvatarServiceImpl implements AvatarService {

	private final List<NicknameValidator> nicknameValidatorList;
	private final AvatarReader avatarReader;
	private final ProfilePhotoFactory profilePhotoFactory;

	@Override
	public AvatarInfo.ValidNickname isValid(AvatarCommand.ChangeNickname command) {

		for (NicknameValidator validator : nicknameValidatorList) {
			AvatarInfo.ValidNickname validationResult = validator.validate(command);
			if (!validationResult.isValid())
				return validationResult;
		}

		return AvatarInfo.ValidNickname.valid();
	}

	@Transactional
	@Override
	public AvatarInfo.ValidNickname changeNickname(Long avatarId, AvatarCommand.ChangeNickname command) {
		AvatarInfo.ValidNickname result = isValid(command);
		if (!result.isValid())
			throw new InvalidNicknameException(result.getErrorCode(), result.getMessage());

		Avatar avatar = avatarReader.getById(avatarId);
		avatar.changeNickname(command.getNickname());
		return result;
	}

	@Transactional
	@Override
	public void changePhoto(Long avatarId, AvatarCommand.ChangePhoto command, MultipartFile image) {
		Avatar avatar = avatarReader.getById(avatarId);
		profilePhotoFactory.store(avatar, command, image);
	}

}
