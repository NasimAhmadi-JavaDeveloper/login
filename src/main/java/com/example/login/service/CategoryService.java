package com.example.login.service;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.mapper.CategoryMapper;
import com.example.login.model.entity.Category;
import com.example.login.model.request.CategoryRequest;
import com.example.login.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryRequest.Select getCategoryById(Integer id) {
        Category category = getCategory(id);
        return categoryMapper.toSelect(category);
    }

    public void createCategory(CategoryRequest.Insert insertRequest) {
        Category category = categoryMapper.toEntity(insertRequest);

        if (insertRequest.getParentId() != null) {
            Category parent = getCategory(insertRequest.getParentId());
            category.setParent(parent);
        }

        categoryRepository.save(category);
    }

    public void updateCategory(CategoryRequest.Update updateRequest) {
        Category existingCategory = getCategory(updateRequest.getId());

        Category updatedCategory = categoryMapper.toEntity(updateRequest);
        updatedCategory.setParent(existingCategory.getParent());
        updatedCategory.setSubcategories(existingCategory.getSubcategories());
        updatedCategory.setProducts(existingCategory.getProducts());

        categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(Integer id) {
        Category category = getCategory(id);
        categoryRepository.delete(category);
    }

    private Category getCategory(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new LogicalException(ExceptionSpec.CATEGORY_NOT_FOUND));
    }
}
