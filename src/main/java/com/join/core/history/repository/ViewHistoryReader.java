package com.join.core.history.repository;

import com.join.core.study.domain.Study;

import java.util.List;

public interface ViewHistoryReader {

    List<Study> getStudyByAvatarId(Long avatarId);
}
