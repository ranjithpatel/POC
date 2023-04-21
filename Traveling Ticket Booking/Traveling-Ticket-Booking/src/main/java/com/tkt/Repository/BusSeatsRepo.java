package com.tkt.Repository;

import com.tkt.entity.BusSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusSeatsRepo extends JpaRepository<BusSeats,Integer> {
}
