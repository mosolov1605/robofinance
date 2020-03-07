package ru.mosolov.robofinance.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mosolov.robofinance.service.AddressNotFoundException;
import ru.mosolov.robofinance.service.CustomerNotFoundException;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> customerNotFound(CustomerNotFoundException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("message", "Customer with id: "+ exception.getMessage() + " is not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Object> addressNotFoundException(AddressNotFoundException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("message", "Address with id: "+ exception.getMessage() + " is not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
