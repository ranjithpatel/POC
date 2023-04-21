package com.tkt.Service;

import com.tkt.model.BusDto;
import com.tkt.model.RetrieveResponse;
import com.tkt.model.TrainDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

public interface StationService {
    ResponseEntity<List<BusDto>> getBusByLiveStation(String name, LocalTime time);
    ResponseEntity<List<TrainDto>> getTrainByLiveLocation(String name, LocalTime time);
}
