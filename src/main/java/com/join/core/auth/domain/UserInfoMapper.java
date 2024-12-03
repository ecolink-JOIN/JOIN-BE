package com.join.core.auth.domain;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserInfoMapper {

	@Mapping(source = "user.id", target = "userId")
	@Mapping(expression = "java(user.getAvatar().getId())", target = "avatarId")
	@Mapping(expression = "java(user.getAvatar().getAvatarToken())", target = "avatarToken")
	@Mapping(expression = "java(user.getAvatar().getNickname())", target = "nickname")
	@Mapping(expression = "java(user.getAuthorities())", target = "authorities")
	@Mapping(constant = "true", target = "registered")
	@Mapping(source = "newUser", target = "newUser")
	@Mapping(expression = "java(user.isTermsAgreed())", target = "termsAgreed")
	@Mapping(expression = "java(user.getAvatar().isNicknameSet())", target = "nicknameSet")
	UserInfo.SigIn of(User user, boolean newUser);

}
