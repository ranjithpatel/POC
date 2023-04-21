package com.tkt.model;

import com.tkt.entity.TrainSeats;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainCoachDto {

    private int id;
    private String coachName;
    private int totalSeats;
    private int availableSeats;
    private List<TrainSeatsDto> trainSeatsDtoList;
}
