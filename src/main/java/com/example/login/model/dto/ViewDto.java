package com.example.login.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ViewDto {
    @JsonProperty("@id")
    private String id;
    @JsonProperty("@type")
    private String type;
    private String first;
    private String next;
    private String last;
}
