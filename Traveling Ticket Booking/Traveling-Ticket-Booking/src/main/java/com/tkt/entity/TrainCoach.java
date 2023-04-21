package com.tkt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainCoach {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String coachName;
    private int totalSeats;
    private int availableSeats;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "traincoachid",referencedColumnName = "id")
    private List<TrainSeats> trainSeatsList;
}
