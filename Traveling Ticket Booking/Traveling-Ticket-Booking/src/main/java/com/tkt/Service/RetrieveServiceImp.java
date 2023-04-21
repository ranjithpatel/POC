package com.tkt.Service;

import com.tkt.Repository.BusRepo;
import com.tkt.Repository.TrainRepo;
import com.tkt.entity.Bus;
import com.tkt.entity.Train;
import com.tkt.model.BusDto;
import com.tkt.model.RetrieveResponse;
import com.tkt.model.TrainDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RetrieveServiceImp implements RetrieveService {
    @Autowired
    private TrainRepo trainRepo;
    @Autowired
    private BusRepo busRepo;


    @Override
    public ResponseEntity<RetrieveResponse> getBYSourceAndDestination(String source, String destination) {
        List<Train> bySourceAndDestination = trainRepo.getBySourceAndDestination(source, destination);
        List<Bus> bySourceAndDestination1 = busRepo.getBySourceAndDestination(source, destination);
        List<TrainDto>  trainDtoList= new ArrayList<>();
        List<BusDto> busDtoList=new ArrayList<>();
        List<BusDto> busDtoList1=new ArrayList<>();
        List<TrainDto>  trainDtoList1= new ArrayList<>();
        RetrieveResponse retrieveResponseBoth = new RetrieveResponse();
        RetrieveResponse retrieveResponse = new RetrieveResponse();

        if (bySourceAndDestination.size() > 0 && bySourceAndDestination1.size() > 0) {

            bySourceAndDestination.stream().filter(train -> train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)).forEach(train -> {
                TrainDto trainDto = new TrainDto();
                trainDto.setTrainNumber(train.getTrainNumber());
                trainDto.setSource(train.getSource());
                trainDto.setDestination(train.getDestination());
                trainDto.setStartingTime(train.getStartingTime());
                trainDto.setEndTime(train.getEndTime());
                trainDtoList.add(trainDto);
                retrieveResponseBoth.setTrainDto(trainDtoList);
            });
            bySourceAndDestination1.stream().filter(bus -> bus.getDestination().equalsIgnoreCase(destination) && bus.getSource().equalsIgnoreCase(source)).forEach(bus -> {
                BusDto busDto = new BusDto();
                busDto.setBusNumber(bus.getBusNumber());
                busDto.setSource(bus.getSource());
                busDto.setDestination(bus.getDestination());
                busDto.setStartingTime(bus.getStartingTime());
                busDto.setEndTime(bus.getEndTime());
                busDtoList.add(busDto);
                retrieveResponseBoth.setBusDto(busDtoList);
            });

            return ResponseEntity.ok(retrieveResponseBoth);
        }
        else if (bySourceAndDestination1.size() > 0 || bySourceAndDestination.size() > 0) {

            bySourceAndDestination.stream().filter(train -> train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)).forEach(train -> {
                TrainDto trainDto = new TrainDto();
                trainDto.setTrainNumber(train.getTrainNumber());
                trainDto.setSource(train.getSource());
                trainDto.setDestination(train.getDestination());
                trainDto.setStartingTime(train.getStartingTime());
                trainDto.setEndTime(train.getEndTime());
                trainDtoList1.add(trainDto);
                retrieveResponse.setTrainDto(trainDtoList1);
            });
            bySourceAndDestination1.stream().filter(bus -> bus.getDestination().equalsIgnoreCase(destination) && bus.getSource().equalsIgnoreCase(source)).forEach(bus -> {
                BusDto busDto = new BusDto();
                busDto.setBusNumber(bus.getBusNumber());
                busDto.setSource(bus.getSource());
                busDto.setDestination(bus.getDestination());
                busDto.setStartingTime(bus.getStartingTime());
                busDto.setEndTime(bus.getEndTime());
                busDtoList1.add(busDto);
                retrieveResponse.setBusDto(busDtoList1);
            });
            return ResponseEntity.ok(retrieveResponse);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
