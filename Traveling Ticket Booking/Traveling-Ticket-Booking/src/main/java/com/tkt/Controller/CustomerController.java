package com.tkt.Controller;

import com.tkt.Service.CustomerService;
import com.tkt.Service.CustomerServiceImp;
import com.tkt.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImp serviceImp;
    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDto dto){
        return serviceImp.saveCustomer(dto);
    }
    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerDto dto){
        return serviceImp.updateCustomer(dto);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String email){
        return serviceImp.getCustomer(email);
    }
    @GetMapping("/get")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return serviceImp.getAllCustomers();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable String email){
        return serviceImp.deleteCustomer(email);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllCustomerDetails(){
        return serviceImp.deleteAllCustomers();
    }

}
