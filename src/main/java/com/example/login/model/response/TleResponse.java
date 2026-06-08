package com.example.login.model.response;

import com.example.login.model.dto.ParameterDto;
import com.example.login.model.dto.TleItemDto;
import com.example.login.model.dto.ViewDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TleResponse {

    @JsonProperty("@context")
    private String context;

    @JsonProperty("@id")
    private String id;

    @JsonProperty("@type")
    private String type;

    private Long totalItems;

    private List<TleItemDto> member;

    private ParameterDto parameters;

    private ViewDto view;
}
