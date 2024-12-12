package com.example.login.model.request;

import com.example.login.model.response.CategoryResponse;
import com.example.login.model.response.ProductResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class CategoryRequest {

    @Data
    @Accessors(chain = true)
    public static class Select {
        private Integer id;
        private String name;
        private CategoryResponse parent;
        private List<CategoryResponse> subcategories;
        private List<ProductResponse> products;
    }

    @Data
    @Accessors(chain = true)
    public static class Insert {
        @NotNull
        private String name;
        private Integer parentId;
    }

    @Data
    @Accessors(chain = true)
    public static class Update {
        @NotNull
        private Integer id;
        private String name;
        private Integer parentId;
    }
}