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
public class Stations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationId;
    private String stationName;
    private LocalTime reachTime;
    @Column(name = "distance(KM)")
    private int distance;

    @ManyToMany(mappedBy = "stationsList")
    private List<Bus> busList;

    @ManyToMany(mappedBy = "stationsList")
    private List<Train> trainList;
}
