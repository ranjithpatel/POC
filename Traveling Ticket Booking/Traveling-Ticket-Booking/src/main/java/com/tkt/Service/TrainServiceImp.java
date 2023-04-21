package com.tkt.Service;

import com.tkt.Exception.ResourceNotFoundException;
import com.tkt.Repository.StationRepo;
import com.tkt.Repository.TrainCoachRepository;
import com.tkt.Repository.TrainRepo;
import com.tkt.Repository.TrainSeatsRepository;
import com.tkt.entity.Stations;
import com.tkt.entity.Train;
import com.tkt.entity.TrainCoach;
import com.tkt.entity.TrainSeats;
import com.tkt.model.StationsDto;
import com.tkt.model.TrainCoachDto;
import com.tkt.model.TrainDto;
import com.tkt.model.TrainSeatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImp implements TrainService{
    @Autowired
    private TrainRepo trainRepo;
    @Autowired
    private TrainCoachRepository trainCoachRepository;
    @Autowired
    private TrainSeatsRepository trainSeatsRepository;
    @Autowired
    private StationRepo stationRepo;
    @Override
    public String registerTrain(TrainDto dto) {
        Optional<Train> byId = trainRepo.findById(dto.getTrainNumber());
        if(byId.isEmpty()) {
           Train train = setTrain(dto);
            List<TrainCoachDto> trainCoachDtoList = dto.getTrainCoachDtoList();
            List<TrainCoach> trainCoachList=new ArrayList<>();
            for(TrainCoachDto trainCoachDto: trainCoachDtoList){
                Optional<TrainCoach> trainCoachId = trainCoachRepository.findById(trainCoachDto.getId());
                if(trainCoachId.isEmpty()){
                    TrainCoach trainCoach=setTrainCoach(trainCoachDto);
                    List<TrainSeatsDto> trainSeatsDtoList = trainCoachDto.getTrainSeatsDtoList();
                    List<TrainSeats> trainSeatsList=new ArrayList<>();
                    for(TrainSeatsDto trainSeatsDto: trainSeatsDtoList){
                        Optional<TrainSeats> trainSeatId = trainSeatsRepository.findById(trainSeatsDto.getId());
                        if(trainSeatId.isEmpty()){
                            TrainSeats  trainSeats=setTrainSeats(trainSeatsDto);
                            trainSeatsList.add(trainSeats);
                            trainCoach.setTrainSeatsList( trainSeatsList);
                        }
                        else{
                            return "trainSeatId is already present";
                        }
                    }
                    trainCoachList.add(trainCoach);
                    train.setTrainCoachList(trainCoachList);
                }
                else{
                    return "trainCoachId is already present";
                }

            }
            List<StationsDto> stationsDtoList = dto.getStationsDtoList();
            List<Stations> stationsList=new ArrayList<>();
            for(StationsDto stationsDto: stationsDtoList){
                Optional<Stations> stationId = stationRepo.findById(stationsDto.getStationId());
                if(stationId.isEmpty()){
                    Stations stations=setStations(stationsDto);
                    stationsList.add(stations);
                    train.setStationsList(stationsList);
                }
                else{
                  return  "station Id is already present";
                }
            }

            trainRepo.save(train);
            return "train registered successfully";
        }
        return "train already registered";
    }

    @Override
    public String updateTrain(TrainDto dto) {
        Optional<Train> optional = trainRepo.findById(dto.getTrainNumber());
        if(optional.isPresent()){
            Train train = optional.get();
            train = setTrain(dto);
            List<TrainCoachDto> trainCoachDtoList = dto.getTrainCoachDtoList();
            List<TrainCoach> trainCoachList=new ArrayList<>();
            for(TrainCoachDto trainCoachDto: trainCoachDtoList){
                Optional<TrainCoach> byId = trainCoachRepository.findById(trainCoachDto.getId());
                if(byId.isPresent()){
                    TrainCoach trainCoach = byId.get();
                    trainCoach=setTrainCoach(trainCoachDto);
                    List<TrainSeatsDto> trainSeatsDtoList = trainCoachDto.getTrainSeatsDtoList();
                    List<TrainSeats> trainSeatsList=new ArrayList<>();
                    for(TrainSeatsDto trainSeatsDto: trainSeatsDtoList){
                        Optional<TrainSeats> byId1 = trainSeatsRepository.findById(trainSeatsDto.getId());
                        if(byId1.isPresent()){
                            TrainSeats trainSeats =byId1.get();
                            trainSeats=setTrainSeats(trainSeatsDto);
                            trainSeatsList.add(trainSeats);
                            trainCoach.setTrainSeatsList(trainSeatsList);
                        }
                        else{
                            return "enter the train seat id";
                        }
                    }
                    trainCoachList.add(trainCoach);
                    train.setTrainCoachList(trainCoachList);
                }
                else{
                    return "enter the trainCoach id";
                }
            }
            List<StationsDto> stationsDtoList = dto.getStationsDtoList();
            List<Stations> stationsList=new ArrayList<>();
            for(StationsDto stationsDto:stationsDtoList) {
                Optional<Stations> stationId = stationRepo.findById(stationsDto.getStationId());
                if(stationId.isPresent()){
                    Stations stations = stationId.get();
                    stations=setStations(stationsDto);
                    stationsList.add(stations);
                    train.setStationsList(stationsList);
                }
                else{
                    return "enter the station id";
                }
            }
             trainRepo.save(train);
             return "train details updated successfully";
        }

        return "no trains with train number";
    }

    @Override
    public ResponseEntity<TrainDto> getTrainById(String trainNumber) {
        TrainDto trainData = getTrainData(trainNumber);

            if (trainData != null) {
                return ResponseEntity.ok(trainData);
            }
            throw new ResourceNotFoundException("no trains found with the given number");

    }

    @Override
    public List<TrainDto> getAllTrains() {
        List<Train> trainList = trainRepo.findAll();
        List<TrainDto> trainDtoList = new ArrayList<>();
        for (Train train : trainList) {
            TrainDto trainData = getTrainData(train.getTrainNumber());
            trainDtoList.add(trainData);
        }
        if (!trainDtoList.isEmpty()){
            return trainDtoList;
         }
        throw new ResourceNotFoundException("no trains found in the database");
    }

    @Override
    public String deleteTrain(String trainNumber) {
        Optional<Train> optional = trainRepo.findById(trainNumber);
        if (optional.isPresent())
        {
            Train train = optional.get();
            trainRepo.delete(train);
            return "train was deleted successfully";
        }
        return "no trains found with the Train Number";
    }

    @Override
    public String deleteAllTrainData() {
        trainRepo.deleteAll();
        return "delete all the train data";
    }


    public Train setTrain(TrainDto trainDto){
            Train train = new Train();
            train.setTrainNumber(trainDto.getTrainNumber());
            train.setSource(trainDto.getSource());
            train.setDestination(trainDto.getDestination());
            train.setStartingTime(trainDto.getStartingTime());
            train.setEndTime(trainDto.getEndTime());
            return  train;
    }

    public TrainCoach setTrainCoach(TrainCoachDto trainCoachDto){
                TrainCoach trainCoach = new TrainCoach();
                trainCoach.setId(trainCoachDto.getId());
                trainCoach.setCoachName(trainCoachDto.getCoachName());
                trainCoach.setTotalSeats(trainCoachDto.getTotalSeats());
                trainCoach.setAvailableSeats(trainCoachDto.getAvailableSeats());
                return trainCoach;

    }

    public TrainSeats setTrainSeats(TrainSeatsDto trainSeatsDto){
            TrainSeats trainSeats = new TrainSeats();
                  trainSeats.setId(trainSeatsDto.getId());
                  trainSeats.setSeatNumber(trainSeatsDto.getSeatNumber());
                  return trainSeats;

    }

    public Stations setStations(StationsDto stationsDto){
            Stations stations = new Stations();
                stations.setStationId(stationsDto.getStationId());
                stations.setStationName(stationsDto.getStationName());
                stations.setDistance(stationsDto.getDistance());
                stations.setReachTime(stationsDto.getReachTime());
                return stations;

    }


    public TrainDto getTrainData(String trainNumber){
        Optional<Train> optional = trainRepo.findById(trainNumber);
        TrainDto trainDto=new TrainDto();
        if(optional.isPresent()){
            Train train1 = optional.get();
            trainDto.setTrainNumber(train1.getTrainNumber());
        trainDto.setSource(train1.getSource());
        trainDto.setDestination(train1.getDestination());
        trainDto.setStartingTime(train1.getStartingTime());
        trainDto.setEndTime(train1.getEndTime());
        List<TrainCoach> trainCoachList = train1.getTrainCoachList();
        List<TrainCoachDto> trainCoachDtoList=new ArrayList<>();
        for(TrainCoach trainCoach:trainCoachList){
            TrainCoachDto trainCoachDto=new TrainCoachDto();
            trainCoachDto.setId(trainCoach.getId());
            trainCoachDto.setCoachName(trainCoach.getCoachName());
            trainCoachDto.setTotalSeats(trainCoach.getTotalSeats());
            trainCoachDto.setAvailableSeats(trainCoach.getAvailableSeats());
            List<TrainSeats> trainSeatsList = trainCoach.getTrainSeatsList();
            List<TrainSeatsDto> trainSeatsDtoList=new ArrayList<>();
            for(TrainSeats trainSeats: trainSeatsList){
                TrainSeatsDto trainSeatsDto=new TrainSeatsDto();
                trainSeatsDto.setId(trainSeats.getId());
                trainSeatsDto.setSeatNumber(trainSeats.getSeatNumber());
                trainSeatsDtoList.add(trainSeatsDto);
            }
            trainCoachDto.setTrainSeatsDtoList(trainSeatsDtoList);
            trainCoachDtoList.add(trainCoachDto);
            trainDto.setTrainCoachDtoList(trainCoachDtoList);
        }
        List<Stations> stationsList = train1.getStationsList();
            List<StationsDto> stationsDtoList = new ArrayList<>();
        for(Stations stations:stationsList) {
            StationsDto stationsDto = new StationsDto();
            stationsDto.setStationId(stations.getStationId());
            stationsDto.setStationName(stations.getStationName());
            stationsDto.setDistance(stations.getDistance());
            stationsDto.setReachTime(stations.getReachTime());
            stationsDtoList.add(stationsDto);
            trainDto.setStationsDtoList(stationsDtoList);
        }
            return trainDto;
        }

       return null;
    }
}
