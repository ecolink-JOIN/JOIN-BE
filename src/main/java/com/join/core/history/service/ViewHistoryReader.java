package com.join.core.history.service;

import com.join.core.study.domain.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ViewHistoryReader {

    Page<Study> getStudyByAvatarId(Long avatarId, Pageable pageable);
}
