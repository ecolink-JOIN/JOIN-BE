package com.join.core.avatar.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.dto.ChangePreferenceRequest;

public interface PreferenceStore {

	void changePreferenceOf(Avatar avatar, ChangePreferenceRequest request);

}
