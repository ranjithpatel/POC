package com.tkt.Repository;

import com.tkt.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepo extends JpaRepository<Train,String> {
    @Query(value="select * from train where source=?1 and destination=?2",nativeQuery = true)
    List<Train> getBySourceAndDestination(String source,String destination );
}
