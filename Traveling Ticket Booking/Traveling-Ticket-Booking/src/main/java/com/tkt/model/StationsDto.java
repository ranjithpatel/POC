package com.tkt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationsDto {
    private int stationId;
    private String stationName;
    private LocalTime reachTime;
    private int distance;


}
