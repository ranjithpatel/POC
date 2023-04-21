package com.tkt.Service;

import com.tkt.model.CustomerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDto dto);
    String updateCustomer(CustomerDto dto);
    ResponseEntity<CustomerDto> getCustomer(String email);
    ResponseEntity<List<CustomerDto>> getAllCustomers();
    String deleteCustomer(String email);
    String deleteAllCustomers();
}
