package com.tkt.Repository;

import com.tkt.entity.Stations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepo extends JpaRepository<Stations,Integer> {
   @Query(value = "select * from stations where station_name=?1",nativeQuery = true)
   List<Stations> getByStationName(String name);

}
