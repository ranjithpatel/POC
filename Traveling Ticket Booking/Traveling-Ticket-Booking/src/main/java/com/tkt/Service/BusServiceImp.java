package com.tkt.Service;

import com.tkt.Exception.BusNotFoundException;
import com.tkt.Exception.ResourceNotFoundException;
import com.tkt.Repository.BusRepo;
import com.tkt.Repository.BusSeatsRepo;
import com.tkt.Repository.StationRepo;
import com.tkt.entity.Bus;
import com.tkt.entity.BusSeats;
import com.tkt.entity.Stations;
import com.tkt.model.BusDto;
import com.tkt.model.BusSeatsDto;
import com.tkt.model.StationsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImp implements BusService {
    @Autowired
    private BusRepo busRepo;
    @Autowired
    private BusSeatsRepo busSeatsRepo;
    @Autowired
    private StationRepo stationRepo;

    @Override
    public String saveBusDetails(BusDto dto) {
        Optional<Bus> optional = busRepo.findById(dto.getBusNumber());
        if (optional.isEmpty()) {
            Bus bus = setBus(dto);
            List<BusSeatsDto> busSeatsDtoList = dto.getBusSeatsDtoList();
            List<BusSeats> busSeatsList = new ArrayList<>();
            for (BusSeatsDto busSeatsDto : busSeatsDtoList) {
                Optional<BusSeats> busSeatsId = busSeatsRepo.findById(busSeatsDto.getId());
                if (busSeatsId.isEmpty()) {
                    BusSeats busSeats = setBusSeats(busSeatsDto);
                    busSeatsList.add(busSeats);
                    bus.setBusSeatsList(busSeatsList);
                } else {
                    return "bus seatId already present";
                }
            }
            List<StationsDto> stationsDtoList = dto.getStationsDtoList();
            List<Stations> stationsList = new ArrayList<>();
            for (StationsDto stationsDto : stationsDtoList) {
                Optional<Stations> stationId = stationRepo.findById(stationsDto.getStationId());
                if (stationId.isEmpty()) {
                    Stations stations = setStations(stationsDto);
                    stationsList.add(stations);
                    bus.setStationsList(stationsList);
                } else {
                    return "station id already present";
                }
            }
            busRepo.save(bus);
            return "bus details saved";
        }
        return "the bus details already present";
    }

    @Override
    public String updateBusDetails(BusDto dto) {
        Optional<Bus> optional = busRepo.findById(dto.getBusNumber());
        if (optional.isPresent()) {
            Bus bus = optional.get();
            bus = setBus(dto);
            List<BusSeatsDto> busSeatsDtoList = dto.getBusSeatsDtoList();
            List<BusSeats> busSeatsList=new ArrayList<>();
            for(BusSeatsDto busSeatsDto: busSeatsDtoList){
                Optional<BusSeats> busSeatsId = busSeatsRepo.findById(busSeatsDto.getId());
                if (busSeatsId.isPresent()) {
                    BusSeats busSeats=busSeatsId.get();
                     busSeats=setBusSeats(busSeatsDto);
                    busSeatsList.add(busSeats);
                    bus.setBusSeatsList(busSeatsList);
                }
                else {
                    return "bus seat id not found";
                }
            }
            List<StationsDto> stationsDtoList = dto.getStationsDtoList();
            List<Stations> stationsList=new ArrayList<>();
            for(StationsDto stationsDto: stationsDtoList){
                Optional<Stations> stationId = stationRepo.findById(stationsDto.getStationId());
                if(stationId.isPresent()){
                    Stations stations=stationId.get();
                     stations=setStations(stationsDto);
                    stationsList.add(stations);
                    bus.setStationsList(stationsList);
                }
                else{
                    return "station id not found "+"/n"+"please provide station id ";
                }
            }
            busRepo.save(bus);
            return "bus details updated";
        }
        return "bus number not found";
    }


    @Override
    public List<BusDto> getAllBusDetails() {
        List<Bus> busList = busRepo.findAll();
        List<BusDto> busDtoList = new ArrayList<>();
        for(Bus bus: busList){
            BusDto busDto=getBus(bus.getBusNumber());
            busDtoList.add(busDto);
        }
        if(!busDtoList.isEmpty()){
            return busDtoList;
        }
        else{
            throw  new BusNotFoundException("no buses found in database");
        }

    }

    @Override
    public BusDto getBusById(String busNumber) {
        BusDto busDto=getBus(busNumber);
        if(busDto!=null) {
            return busDto;
        }
        throw new BusNotFoundException("no buses found with the given number");
    }

    @Override
    public String deleteBus(String busNumber) {
        Optional<Bus> byId = busRepo.findById(busNumber);
        if (byId.isPresent()) {
            busRepo.deleteById(busNumber);
            return "bus deleted successfully";
        }
        return "no bus found with the number";
    }

    @Override
    public String deleteAllBuses() {
        busRepo.deleteAll();
        return "All bus details deleted";
    }


    public Bus setBus(BusDto dto) {
        Bus bus = new Bus();
        bus.setBusNumber(dto.getBusNumber());
        bus.setSource(dto.getSource());
        bus.setDestination(dto.getDestination());
        bus.setStartingTime(dto.getStartingTime());
        bus.setEndTime(dto.getEndTime());
        bus.setTotalSeats(dto.getTotalSeats());
        bus.setAvailableSeats(dto.getAvailableSeats());
        return bus;
    }

    public BusSeats setBusSeats(BusSeatsDto seatsDto) {
        BusSeats busSeats = new BusSeats();
        busSeats.setId(seatsDto.getId());
        busSeats.setSeatNumber(seatsDto.getSeatNumber());
        return busSeats;
    }

    public Stations setStations(StationsDto stationsDto) {
        Stations stations = new Stations();
        stations.setStationId(stationsDto.getStationId());
        stations.setStationName(stationsDto.getStationName());
        stations.setReachTime(stationsDto.getReachTime());
        stations.setDistance(stationsDto.getDistance());
        return stations;
    }
    public BusDto getBus(String busNumber) {
        Optional<Bus> optional = busRepo.findById(busNumber);
        BusDto busDto = new BusDto();
        if (optional.isPresent()) {
            Bus bus = optional.get();
            busDto.setBusNumber(bus.getBusNumber());
            busDto.setDestination(bus.getDestination());
            busDto.setSource(bus.getSource());
            busDto.setStartingTime(bus.getStartingTime());
            busDto.setEndTime(bus.getEndTime());
            busDto.setTotalSeats(bus.getTotalSeats());
            busDto.setAvailableSeats(bus.getAvailableSeats());
            List<BusSeats> busSeatsList = bus.getBusSeatsList();
            List<BusSeatsDto> busSeatsDtoList = new ArrayList<>();
            for (BusSeats busSeats : busSeatsList) {
                BusSeatsDto busSeatsDto = new BusSeatsDto();
                busSeatsDto.setId(busSeats.getId());
                busSeatsDto.setSeatNumber(busSeats.getSeatNumber());
                busSeatsDtoList.add(busSeatsDto);
                busDto.setBusSeatsDtoList(busSeatsDtoList);
            }
            List<Stations> stationsList = bus.getStationsList();
            List<StationsDto> stationsDtoList = new ArrayList<>();
            for (Stations stations : stationsList) {
                StationsDto stationsDto = new StationsDto();
                stationsDto.setStationId(stations.getStationId());
                stationsDto.setStationName(stations.getStationName());
                stationsDto.setReachTime(stations.getReachTime());
                stationsDto.setDistance(stations.getDistance());
                stationsDtoList.add(stationsDto);
                busDto.setStationsDtoList(stationsDtoList);
            }
            return busDto;
        }
        return null;
    }
}

