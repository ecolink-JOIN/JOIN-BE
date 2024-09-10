package com.join.core.avatar.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class NicknameFormatValidator implements NicknameValidator {

	private static final Pattern pattern = Pattern.compile("^[가-힣a-zA-Z0-9]{2,7}$");

	@Override
	public AvatarInfo.ValidNickname validate(AvatarCommand.ChangeNickname command) {
		Matcher matcher = pattern.matcher(command.getNickname());
		if (!matcher.matches())
			return new AvatarInfo.ValidNickname(false, "2-7자리 한글/영문/숫자로 입력해주세요.");

		return AvatarInfo.ValidNickname.valid();
	}

}
