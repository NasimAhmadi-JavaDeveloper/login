package com.example.login.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TleItemDto {
    @JsonProperty("@id")
    private String id;

    @JsonProperty("@type")
    private String type;

    private Long satelliteId;

    private String name;

    private String date;

    private String line1;

    private String line2;
}
