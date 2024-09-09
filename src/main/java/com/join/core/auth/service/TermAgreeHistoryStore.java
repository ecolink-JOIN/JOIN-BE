package com.join.core.auth.service;

import com.join.core.auth.domain.TermCommand;
import com.join.core.auth.domain.User;

public interface TermAgreeHistoryStore {

	void store(User user, TermCommand.Agree agree);

}
