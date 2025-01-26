package com.example.Airport.reservation;


import com.example.Airport.flight.Flight;
import com.example.Airport.flight.FlightRepository;
import com.example.Airport.flight.exceptions.FlightNotFoundException;
import com.example.Airport.reservation.exceptions.FlightAlreadyDepartedException;
import com.example.Airport.reservation.exceptions.NotAvailableSeatsReservationException;
import com.example.Airport.reservation.exceptions.ReservationNotFoundException;
import com.example.Airport.reservation.exceptions.UserNotFoundException;
import com.example.Airport.user.User;
import com.example.Airport.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, FlightRepository flightRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    public ReservationResponse createReservation(ReservationRequest reservationRequest) {
        Flight flight = flightRepository.findById(reservationRequest.flightId())
                .orElseThrow(() -> new FlightNotFoundException("Flight not found."));

        if(flight.getAvailableSeats() < reservationRequest.seatsReserved()) {
            throw new NotAvailableSeatsReservationException("Not enough seats available");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - reservationRequest.seatsReserved());
        flightRepository.save(flight);

        User user = userRepository.findById(reservationRequest.userId())
                .orElseThrow(()-> new UserNotFoundException("User not found."));

        Reservation reservation = ReservationMapper.toEntity(reservationRequest, user, flight, LocalDateTime.now().plusMinutes(1));

        Reservation savedReservation = reservationRepository.save(reservation);

        return ReservationMapper.toResponse(savedReservation);
    }

    public ReservationResponse confirmReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation Not Found."));

        if (!reservation.getReservationStatus().equals(ReservationStatus.PENDING)) {
            throw new IllegalStateException("Reservation is not in PENDING status.");
        }

        Flight flight = reservation.getFlight();
        if(flight.getDepartureDateTime().isBefore(LocalDateTime.now())) {
            throw new FlightAlreadyDepartedException("Flight has already departed.");
        }

        reservation.setReservationStatus(ReservationStatus.CONFIRMED);
        Reservation updatedReservation = reservationRepository.save(reservation);

        return ReservationMapper.toResponse(updatedReservation);
    }

    public List<ReservationResponse> findAll() {
        List<Reservation> reservationList = reservationRepository.findAll();

        if(reservationList.isEmpty()) {
            throw new ReservationNotFoundException("There is not reservations to show");
        }

        return reservationList.stream()
                .map(ReservationMapper::toResponse)
                .collect(Collectors.toList());

        // TODO implementar busqueda de reservas por id de usuario y mostrar flight details
    }

    public ReservationResponse findById(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if(optionalReservation.isEmpty()) {
            throw new ReservationNotFoundException("The reservation with id " + id + " does not exist.");
        }

        Reservation reservation = optionalReservation.get();
        return ReservationMapper.toResponse(reservation);
    }

    public void deleteReservation(Long id) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation with ID " + id + " not found"));

        reservationRepository.delete(existingReservation);

        //TODO el usuario tiene que estar autenticado para eliminar la reserva
    }
}
