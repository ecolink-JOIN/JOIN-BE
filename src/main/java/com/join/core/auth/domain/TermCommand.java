package com.join.core.auth.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TermCommand {

	private TermCommand() {
	}

	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class Agree {
		private List<AgreeTerm> terms;
	}

	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class AgreeTerm {
		private Long id;
		private String version;
		private TermAgreeHistory.AcceptStatus status;

		public Term.Key toKey() {
			return new Term.Key(id, version);
		}

	}

}
