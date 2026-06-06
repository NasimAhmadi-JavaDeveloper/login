package com.example.login.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteDto {
    private Long satelliteId;
    private String name;
    private String date;
    private String line1;
    private String line2;
}
