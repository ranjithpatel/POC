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
public class BusDto {



    private String busNumber;
    private LocalTime startingTime;
    private LocalTime endTime;
    private int totalSeats;
    private int availableSeats;
    private String source;
    private String destination;

    private List<BusSeatsDto> busSeatsDtoList;
    private List<StationsDto> stationsDtoList;

}
