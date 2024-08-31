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
public interface TermInfoMapper {

	@Mapping(source = "term.key.id", target = "id")
	@Mapping(source = "term.key.version", target = "version")
	TermInfo.Main of(Term term);

}
