package com.join.core.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.TermInfo;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TermService {

	private final TermReader termReader;

	public List<TermInfo.Main> getRequiredConsentTerms(Long userId) {
		return termReader.getRequiredConsentTerms(userId);
	}

}
