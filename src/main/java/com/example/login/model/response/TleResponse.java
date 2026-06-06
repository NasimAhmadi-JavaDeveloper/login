package com.example.login.model.response;

import com.example.login.model.dto.SatelliteDto;
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

    private Integer totalItems;

    @JsonProperty("member")
    private List<SatelliteDto> satellites;
}
