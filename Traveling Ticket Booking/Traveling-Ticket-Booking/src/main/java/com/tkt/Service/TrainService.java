package com.tkt.Service;

import com.tkt.model.TrainDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainService {
    String registerTrain(TrainDto dto);
    String updateTrain(TrainDto dto);
   ResponseEntity<TrainDto> getTrainById(String trainNumber);
    List<TrainDto> getAllTrains ();
    String deleteTrain(String trainNumber);
    String deleteAllTrainData();
}
