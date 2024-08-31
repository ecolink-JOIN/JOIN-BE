package com.join.core.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.join.core.auth.domain.Term;

public interface TermRepository extends JpaRepository<Term, Term.Key> {
	@Query("select t from Term t"
		   + " left join TermAgreeHistory ta"
		   + " on t = ta.term and ta.user.id = :userId"
		   + " where CURRENT_DATE between t.startDate and t.endDate"
		   + " and ta is null")
	List<Term> findRequiredConsentWith(@Param("userId") Long userId);
}
