package com.tkt.Service;

import com.tkt.Exception.ResourceNotFoundException;
import com.tkt.Repository.StationRepo;
import com.tkt.entity.Bus;
import com.tkt.entity.Stations;
import com.tkt.model.BusDto;
import com.tkt.model.RetrieveResponse;
import com.tkt.model.TrainDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StationServiceImp implements StationService{
    @Autowired
    private StationRepo stationRepo;
    @Autowired
    private TrainServiceImp serviceImp;
    @Override
    public ResponseEntity<List<BusDto>> getBusByLiveStation(String name, LocalTime time) {
        List<Stations> byStationName = stationRepo.getByStationName(name);
        List<BusDto> busDtoList=new ArrayList<>();
        if(byStationName.size()>0) {
            byStationName.stream().filter(station -> station.getStationName().equalsIgnoreCase(name) && station.getReachTime().equals(time))
                    .forEach(stations -> stations.getBusList().stream().forEach(bus -> {
                        BusDto busDto = new BusDto();
                        busDto.setBusNumber(bus.getBusNumber());
                        busDto.setSource(bus.getSource());
                        busDto.setDestination(bus.getDestination());
                        busDto.setStartingTime(bus.getStartingTime());
                        busDto.setEndTime(bus.getEndTime());
                        busDtoList.add(busDto);
                    }));
                return ResponseEntity.ok(busDtoList);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TrainDto>> getTrainByLiveLocation(String name, LocalTime time) {
        List<Stations> byStationName = stationRepo.getByStationName(name);
        List<TrainDto> trainDtoList=new ArrayList<>();
        if(byStationName.size()>0){
            byStationName.stream().filter(station -> station.getStationName().equalsIgnoreCase(name) && station.getReachTime().equals(time))
                    .forEach(stations -> stations.getTrainList().stream().forEach(train -> {
                            TrainDto  trainDto=new TrainDto();
                           trainDto.setTrainNumber(train.getTrainNumber());
                           trainDto.setStartingTime(train.getStartingTime());
                           trainDto.setDestination(train.getDestination());
                           trainDto.setEndTime(train.getEndTime());
                           trainDto.setSource(train.getSource());
                           trainDtoList.add(trainDto);

                    }));
            return ResponseEntity.ok(trainDtoList);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
