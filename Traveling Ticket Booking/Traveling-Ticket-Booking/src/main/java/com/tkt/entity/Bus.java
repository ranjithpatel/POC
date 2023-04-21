package com.tkt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {


    @Id
    private String busNumber;
    private LocalTime startingTime;
    private LocalTime endTime;
    private int totalSeats;
    private int availableSeats;
    private String source;
    private String destination;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "busNumber", referencedColumnName = "busNumber")
    private List<BusSeats> busSeatsList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "busNumber", referencedColumnName = "busNumber")
    private List<Stations> stationsList;



}
