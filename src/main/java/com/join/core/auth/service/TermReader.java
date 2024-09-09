package com.join.core.auth.service;

import java.util.Collection;
import java.util.List;

import com.join.core.auth.domain.Term;
import com.join.core.auth.domain.TermInfo;

public interface TermReader {

	List<TermInfo.Main> getRequiredConsentTerms(Long userId);

	List<Term> getTerms(Collection<Term.Key> keys);

}
