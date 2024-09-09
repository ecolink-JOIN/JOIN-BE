package com.join.core.auth.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

public class TermCommand {

	private TermCommand() {
	}

	@Builder
	@Getter
	public static class Agree {
		private final List<AgreeTerm> terms;
	}

	@Builder
	@Getter
	public static class AgreeTerm {
		private final Long id;
		private final String version;
		private final TermAgreeHistory.AcceptStatus status;

		public Term.Key toKey() {
			return new Term.Key(id, version);
		}

	}

}
