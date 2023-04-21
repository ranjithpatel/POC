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
public class Train{
    @Id
    private String trainNumber;
    private LocalTime startingTime;
    private LocalTime endTime;
    private String source;
    private String destination;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainNumber", referencedColumnName = "trainNumber")
    private List<TrainCoach> trainCoachList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainNumber", referencedColumnName = "trainNumber")
    private List<Stations> stationsList;

}
