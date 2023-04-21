package com.tkt.Service;

import com.tkt.model.BusDto;

import java.util.List;

public interface BusService {
     String saveBusDetails(BusDto dto);
    String updateBusDetails(BusDto dto);
    List<BusDto> getAllBusDetails();
    BusDto getBusById(String busNumber);
    String deleteBus(String busNumber);
    String deleteAllBuses();
}
