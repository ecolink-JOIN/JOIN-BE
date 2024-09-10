package com.join.core.avatar.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AvatarServiceImpl implements AvatarService {

	private final List<NicknameValidator> nicknameValidatorList;

	@Override
	public AvatarInfo.ValidNickname isValid(AvatarCommand.ChangeNickname command) {

		for (NicknameValidator validator : nicknameValidatorList) {
			AvatarInfo.ValidNickname validationResult = validator.validate(command);
			if (!validationResult.isValid())
				return validationResult;
		}

		return AvatarInfo.ValidNickname.valid();
	}

}