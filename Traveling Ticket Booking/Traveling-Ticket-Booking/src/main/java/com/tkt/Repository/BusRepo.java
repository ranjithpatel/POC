package com.tkt.Repository;

import com.tkt.entity.Bus;
import com.tkt.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepo extends JpaRepository<Bus,String> {
    @Query(value="select * from bus where source=?1 and destination=?2",nativeQuery = true)
    List<Bus> getBySourceAndDestination(String source, String destination );
}
