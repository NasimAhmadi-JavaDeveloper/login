package com.example.login.mapper;

import com.example.login.model.entity.Category;
import com.example.login.model.request.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryRequest.Select toSelect(Category category);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "subcategories", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryRequest.Insert insert);

    @Mapping(target = "subcategories", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "parent", ignore = true)
    Category toEntity(CategoryRequest.Update update);
}