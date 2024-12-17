package com.join.core.avatar.domain;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AvatarInfoMapper {

	@Mapping(source = "avatar.photo.file", target = "image")
	@Mapping(source = "avatar.user.email", target = "email")
	@Mapping(source = "avatar.user.singUpDate", target = "singUpDate")
	@Mapping(source = "avatar.user.status", target = "status")
	@Mapping(source = "avatar.user.platform", target = "platform")
	AvatarInfo.Self of(Avatar avatar);

}
