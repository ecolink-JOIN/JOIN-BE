package com.join.core.category.repository;

import com.join.core.category.domain.Category;
import com.join.core.category.service.CategoryReader;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.InvalidSelectionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CategoryReaderImpl implements CategoryReader {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new InvalidSelectionException(ErrorCode.CATEGORY_SELECTION_REQUIRED));
    }

}
