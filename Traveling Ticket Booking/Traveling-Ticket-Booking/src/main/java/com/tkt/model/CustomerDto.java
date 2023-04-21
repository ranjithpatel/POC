package com.tkt.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String email;
    private String name;
    private long phone;
    private LocalDate dob;


}
