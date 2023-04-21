package com.tkt.Repository;

import com.tkt.entity.TrainCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainCoachRepository extends JpaRepository<TrainCoach,Integer> {

}
