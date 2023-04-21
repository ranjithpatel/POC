package com.tkt.model;

import com.tkt.entity.Stations;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainDto{
    private String trainNumber;
    private LocalTime startingTime;
    private LocalTime endTime;
    private String source;
    private String destination;

    private List<TrainCoachDto> trainCoachDtoList;

    private List<StationsDto> stationsDtoList;

}
