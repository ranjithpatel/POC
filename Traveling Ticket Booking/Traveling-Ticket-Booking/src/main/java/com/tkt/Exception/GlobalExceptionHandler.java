package com.tkt.Exception;

import com.tkt.Service.TrainServiceImp;
import com.tkt.model.ErrorResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleResourceNotFoundException(ResourceNotFoundException notFoundException) {

       ErrorResponseDto responseDto=new ErrorResponseDto();
       responseDto.setMessage(notFoundException.getMessage());
       return responseDto;

    }

    @ExceptionHandler(BusNotFoundException.class)
    public ErrorResponseDto handleBusNotFoundException(BusNotFoundException busNotFoundException){
        ErrorResponseDto responseDto=new ErrorResponseDto();
        responseDto.setMessage(busNotFoundException.getMessage());
        return responseDto;
    }
}
