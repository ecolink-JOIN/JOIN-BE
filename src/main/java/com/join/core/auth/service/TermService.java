package com.join.core.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.TermCommand;
import com.join.core.auth.domain.TermInfo;
import com.join.core.auth.domain.User;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TermService {

	private final TermReader termReader;
	private final TermAgreeHistoryStore termAgreeHistoryStore;
	private final UserReader userReader;

	public List<TermInfo.Main> getRequiredConsentTerms(Long userId) {
		return termReader.getRequiredConsentTerms(userId);
	}

	@Transactional
	public void agreeTerm(Long userId, TermCommand.Agree command) {
		User user = userReader.getUser(userId);
		termAgreeHistoryStore.store(user, command);
	}

}
