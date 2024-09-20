package com.join.core.avatar.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.common.exception.impl.InvalidNicknameException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AvatarServiceImpl implements AvatarService {

	private final List<NicknameValidator> nicknameValidatorList;
	private final AvatarReader avatarReader;

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

}
