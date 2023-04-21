package com.tkt.Controller;

import com.tkt.Service.TrainServiceImp;
import com.tkt.model.TrainDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private TrainServiceImp serviceImp;
    @PostMapping("/save")
    public  String registerTrain(@RequestBody TrainDto dto ){
        return serviceImp.registerTrain(dto);
    }

    @PutMapping("/update")
    public String updateTrain(@RequestBody TrainDto dto)
    {
        return  serviceImp.updateTrain(dto);
    }
    @GetMapping("/get")
    public ResponseEntity<TrainDto> getTrainDetails(@RequestParam String trainNumber){
        return serviceImp.getTrainById(trainNumber);
    }
    @GetMapping("/getAll")
    public List<TrainDto> getAllTrains(){
        return serviceImp.getAllTrains();
    }
    @DeleteMapping("/delete")
    public String deleteTrain(@RequestParam String trainNumber){
        return serviceImp.deleteTrain(trainNumber);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllTrains(){
        return serviceImp.deleteAllTrainData();
    }
}
