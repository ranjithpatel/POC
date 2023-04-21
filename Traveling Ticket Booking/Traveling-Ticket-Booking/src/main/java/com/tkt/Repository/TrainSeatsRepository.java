package com.tkt.Repository;

import com.tkt.entity.TrainSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainSeatsRepository extends JpaRepository<TrainSeats,Integer> {
}
