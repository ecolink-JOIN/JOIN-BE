package com.join.core.category.service;

import com.join.core.category.domain.Category;

public interface CategoryReader {
    Category getCategoryByName(String categoryName);

}
