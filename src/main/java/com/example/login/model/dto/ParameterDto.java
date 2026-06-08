package com.example.login.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParameterDto {

    private String search;

    private String sort;

    @JsonProperty("sort-dir")
    private String sortDir;

    private Integer page;

    @JsonProperty("page-size")
    private Integer pageSize;
}
