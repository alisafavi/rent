package com.example.rent.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;

@ControllerAdvice
public class ExceptionController {

//    @Resource
//    private com.soltani.service.service service;

    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<String> NotFoundId() {
        return new ResponseEntity<>("\"timestamp\":" + LocalTime.now() + "\n \"status\":" + HttpStatus.EXPECTATION_FAILED.value() + "\n \"error\": not found Id", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntityAlreadyExsist.class)
    public ResponseEntity<String> EntityAlreadyExists() {
        return new ResponseEntity<>("\"timestamp\":" + LocalTime.now() + "\n \"status\":" + HttpStatus.EXPECTATION_FAILED.value() + "\n \"error\": Entity Already Exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequest.class)
    public ResponseEntity<String> BadRequest() {
        return new ResponseEntity<>("\"timestamp\":" + LocalTime.now() + "\n \"status\":" + HttpStatus.EXPECTATION_FAILED.value() + "\n \"error\": Entity Already Exists", HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = ValidationException.class)
//    public ResponseEntity<String> badRequest(ValidationException exception) {
//        return new ResponseEntity<>("\"timestamp\":" + LocalTime.now() + "\n \"status\":" + HttpStatus.EXPECTATION_FAILED.value() + "\n \"error\":" + service.getViolations(), HttpStatus.EXPECTATION_FAILED);
//    }
}
