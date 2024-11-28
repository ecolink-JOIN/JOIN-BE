package com.join.core.history.repository;

import com.join.core.history.domain.ViewHistory;

import java.util.List;

public interface ViewHistoryReader {

    List<ViewHistory> getViewsByAvatarId(Long avatarId);
}
