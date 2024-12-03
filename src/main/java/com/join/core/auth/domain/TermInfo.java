package com.join.core.auth.domain;

import lombok.Builder;
import lombok.Getter;

public class TermInfo {

	private TermInfo() {
	}

	@Builder
	@Getter
	public static class Main {
		private final Long id;
		private final String version;
		private final String title;
		private final String content;
		private final Term.Type type;
	}

}
