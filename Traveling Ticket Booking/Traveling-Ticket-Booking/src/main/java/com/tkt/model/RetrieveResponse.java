package com.tkt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveResponse {
    private List<BusDto> busDto;
    private List<TrainDto> trainDto;

}
