package com.join.core.auth.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.auth.domain.Term;
import com.join.core.auth.domain.TermInfo;
import com.join.core.auth.domain.TermInfoMapper;
import com.join.core.auth.service.TermReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class TermReaderImpl implements TermReader {

	private final TermRepository termRepository;
	private final TermInfoMapper termInfoMapper;

	@Override
	public List<TermInfo.Main> getRequiredConsentTerms(Long userId) {
		List<Term> terms = termRepository.findRequiredConsentWith(userId);
		return terms.stream().map(termInfoMapper::of).toList();
	}
}
