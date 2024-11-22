package com.example.login.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public abstract class PostRequest {

    @Data
    @Accessors(chain = true)
    public static class PostCreateDto {
        @NotNull
        private Integer userId;
        @NotEmpty
        private String caption;
        private List<String> imageUrls;
        private List<String> tag;
    }
}
