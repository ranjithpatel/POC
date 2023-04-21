package com.tkt.Controller;

import com.tkt.Service.BusServiceImp;
import com.tkt.model.BusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {
    @Autowired
    private BusServiceImp serviceImp;
    @PostMapping("/save")
    public String saveBusDetails(@RequestBody BusDto dto){
        return serviceImp.saveBusDetails(dto);
    }
    @PutMapping("/update")
    public String updateBusDetails(@RequestBody BusDto dto){
        return serviceImp.updateBusDetails(dto);
    }
    @GetMapping("/getAll")
    public List<BusDto> getAllBusDetails(){
        return serviceImp.getAllBusDetails();
    }
    @GetMapping("/get/{id}")
    public BusDto getBusDetails(@PathVariable String id){
        return serviceImp.getBusById(id);
    }
    @DeleteMapping("/delete/{busNumber}")
    public String deleteBus(@PathVariable String busNumber){
        return serviceImp.deleteBus(busNumber);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllBUdDetails(){
        return serviceImp.deleteAllBuses();
    }
}
