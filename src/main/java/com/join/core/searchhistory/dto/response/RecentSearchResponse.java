package com.join.core.searchhistory.dto.response;

import java.time.LocalDateTime;

public record RecentSearchResponse(Long id, String keyword, LocalDateTime createdDate) {
}
