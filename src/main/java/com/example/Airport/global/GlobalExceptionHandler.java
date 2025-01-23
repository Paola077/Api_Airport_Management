package com.example.Airport.global;

import com.example.Airport.airport.exceptions.AirportAlreadyExistException;
import com.example.Airport.airport.exceptions.AirportInvalidModificationException;
import com.example.Airport.airport.exceptions.AirportNotFoundException;
import com.example.Airport.flight.exceptions.FlightInvalidOriginAndDestination;
import com.example.Airport.flight.exceptions.FlightInvalidSeatsException;
import com.example.Airport.flight.exceptions.FlightNotFoundException;
import com.example.Airport.user.exceptions.ExistingUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistingUserException.class)
    public ResponseEntity<Map<String, String>> handleAirportExistingUserException(ExistingUserException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AirportAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleAirportAlreadyExistException(AirportAlreadyExistException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAirportNotFoundException(AirportNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AirportInvalidModificationException.class)
    public ResponseEntity<Map<String, String>> handleAirportInvalidModificationException(AirportInvalidModificationException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FlightInvalidOriginAndDestination.class)
    public ResponseEntity<Map<String, String>> handleFlightInvalidOriginAndDestination(FlightInvalidOriginAndDestination exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleFlightNotFoundException(FlightNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    //FlightInvalidSeats
    @ExceptionHandler(FlightInvalidSeatsException.class)
    public ResponseEntity<Map<String, String>> handleFlightInvalidSeatsException(FlightInvalidSeatsException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}
