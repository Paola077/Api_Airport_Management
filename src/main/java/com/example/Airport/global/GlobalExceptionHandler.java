package com.example.Airport.global;

import com.example.Airport.airport.exceptions.AirportAlreadyExistException;
import com.example.Airport.airport.exceptions.AirportInvalidModificationException;
import com.example.Airport.airport.exceptions.AirportNotFoundException;
import com.example.Airport.flight.exceptions.FlightInvalidOriginAndDestination;
import com.example.Airport.flight.exceptions.FlightInvalidSeatsException;
import com.example.Airport.flight.exceptions.FlightNotFoundException;
import com.example.Airport.profile.exceptions.ProfileAlreadyExistException;
import com.example.Airport.reservation.exceptions.FlightAlreadyDepartedException;
import com.example.Airport.reservation.exceptions.NotAvailableSeatsReservationException;
import com.example.Airport.reservation.exceptions.ReservationNotFoundException;
import com.example.Airport.reservation.exceptions.UserNotFoundException;
import com.example.Airport.user.exceptions.UserAlreadyExistException;
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

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleAirportExistingUserException(UserAlreadyExistException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException exception) {
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

    @ExceptionHandler(FlightInvalidSeatsException.class)
    public ResponseEntity<Map<String, String>> handleFlightInvalidSeatsException(FlightInvalidSeatsException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotAvailableSeatsReservationException.class)
    public ResponseEntity<Map<String, String>> handleNotAvailableSeatsReservationException(NotAvailableSeatsReservationException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleReservationNotFoundException(ReservationNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightAlreadyDepartedException.class)
    public ResponseEntity<Map<String, String>> handleFlightAlreadyDepartedException(FlightAlreadyDepartedException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    //ProfileAlreadyExistException
    @ExceptionHandler(ProfileAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleProfileAlreadyExistException(ProfileAlreadyExistException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
