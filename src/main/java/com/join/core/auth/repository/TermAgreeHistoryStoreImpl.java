package com.join.core.auth.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.Term;
import com.join.core.auth.domain.TermCommand;
import com.join.core.auth.domain.User;
import com.join.core.auth.service.TermAgreeHistoryStore;
import com.join.core.auth.service.TermReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.TermAgreeException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TermAgreeHistoryStoreImpl implements TermAgreeHistoryStore {

	private final TermReader termReader;

	@Transactional
	@Override
	public void store(User user, TermCommand.Agree agree) {
		List<Term.Key> keys = agree.getTerms().stream().map(TermCommand.AgreeTerm::toKey).toList();
		List<Term> terms = termReader.getTerms(keys);
		terms.forEach(t -> {
			Term.Key key = t.getKey();
			TermCommand.AgreeTerm agreeTerm = agree.getTerms().stream().filter(at -> at.toKey().equals(key)).findFirst()
				.orElseThrow(() -> new TermAgreeException(ErrorCode.TERM_NOT_EXIST));
			user.agree(t, agreeTerm.getStatus());
		});

	}
}
