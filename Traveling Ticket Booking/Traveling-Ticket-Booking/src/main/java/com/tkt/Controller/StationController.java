package com.tkt.Controller;

import com.tkt.Service.StationServiceImp;
import com.tkt.model.BusDto;
import com.tkt.model.RetrieveResponse;
import com.tkt.model.TrainDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationServiceImp stationServiceImp;
    @GetMapping("/getBus/{name}/{time}")
    public ResponseEntity<List<BusDto>> getBusByLiveLocation(@PathVariable String name,@PathVariable LocalTime  time){
        return stationServiceImp.getBusByLiveStation(name,time);
    }
    @GetMapping("/getTrain/{name}/{time}")
    public ResponseEntity<List<TrainDto>> getTrainByLiveLocation(@PathVariable String name, @PathVariable LocalTime  time){
        return stationServiceImp.getTrainByLiveLocation(name,time);
    }

}
