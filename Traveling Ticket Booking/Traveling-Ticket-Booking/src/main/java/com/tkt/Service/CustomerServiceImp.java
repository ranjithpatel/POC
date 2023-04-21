package com.tkt.Service;

import com.tkt.Repository.CustomerRepository;
import com.tkt.entity.Customer;
import com.tkt.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService{
    @Autowired
    private CustomerRepository repository;
    @Override
    public String saveCustomer(CustomerDto dto) {
        Optional<Customer> optional = repository.findById(dto.getEmail());
        Customer customer=new Customer();
        LocalDate localDate = LocalDate.now();
        if(optional.isPresent()){
            return "customer already exist";
        }
        else{
            int age = calculateAge((dto.getDob()), localDate);
            if(age>=18) {
                customer.setName(dto.getName());
                customer.setEmail(dto.getEmail());
                customer.setPhone(dto.getPhone());
                customer.setDob(((dto.getDob())));
                repository.save(customer);
                return "customer saved successfully";
            }
            else{
                return "the customer is below 18 years";
            }
        }

    }



    @Override
    public String updateCustomer(CustomerDto dto) {
        Optional<Customer> optional = repository.findById(dto.getEmail());
        LocalDate localDate=LocalDate.now();
        if(optional.isPresent()) {
            int age = calculateAge((dto.getDob()), localDate);
            if (age >= 18) {
                Customer customer = optional.get();
                customer.setName(dto.getName());
                customer.setEmail(dto.getEmail());
                customer.setPhone(dto.getPhone());
                customer.setDob((dto.getDob()));
                repository.save(customer);
                return "customer updated";
            }
            else {
                return "updated data should contain age above 18 years";
            }
        }
        return "customer doesn't exist";
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomer(String id) {
        Optional<Customer> optional = repository.findById(id);
        CustomerDto dto=new CustomerDto();
        if(optional.isPresent()){
            Customer customer = optional.get();
            dto.setName(customer.getName());
            dto.setEmail(customer.getEmail());
            dto.setPhone(customer.getPhone());
            dto.setDob((customer.getDob()));
            return  ResponseEntity.ok(dto);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> customers = repository.findAll();
        List<CustomerDto> dto1=new ArrayList<>();
        CustomerDto dto=new CustomerDto();
        if(customers!=null) {
            for(Customer customer:customers ) {
                dto.setName(customer.getName());
                dto.setEmail(customer.getEmail());
                dto.setPhone(customer.getPhone());
                dto.setDob((customer.getDob()));
                dto1.add(dto);
            }
            return ResponseEntity.ok(dto1);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public String deleteCustomer(String emailid) {
        Optional<Customer> optional = repository.findById(emailid);
        if (optional.isPresent()){
            repository.deleteById(emailid);
        return "the customer has deleted successfully";
    }
        return "customer not found";
        
    }

    @Override
    public String deleteAllCustomers() {
        repository.deleteAll();
        return "All customer details deleted";
    }


    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        }
        return 0;
    }
}
