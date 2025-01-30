package com.example.Airport.reservation;

import com.example.Airport.flight.Flight;
import com.example.Airport.flight.FlightRepository;
import com.example.Airport.flight.FlightStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Component
public class ReservationHandler {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;

    public ReservationHandler(ReservationRepository reservationRepository,
                              FlightRepository flightRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void releaseExpiredReservations() {
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expiredReservations = reservationRepository.findAll()
                .stream()
                .filter(r -> r.getReservationStatus().equals(ReservationStatus.PENDING))
                .filter(r -> r.getReservationExpirationTime().isBefore(now))
                .toList();

        for (Reservation reservation : expiredReservations) {
            Flight flight = reservation.getFlight();
            flight.setAvailableSeats(flight.getAvailableSeats() + reservation.getSeatsReserved());
            flightRepository.save(flight);

            reservationRepository.delete(reservation);
        }
    }

    @Scheduled(fixedRate = 86400000)
    public void cleanUpConfirmedReservations() {
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expiredReservations = reservationRepository.findAll()
                .stream()
                .filter(r -> r.getReservationStatus().equals(ReservationStatus.CONFIRMED))
                .filter(r -> r.getFlight().getDepartureDateTime().isBefore(now))
                .toList();

        for (Reservation reservation : expiredReservations) {
            reservationRepository.delete(reservation);
        }
    }


    @Scheduled(fixedRate = 15000)
    public void updateFlightStatuses() {
        List<Flight> activeFlights = flightRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        for (Flight flight : activeFlights) {

            if (flight.getDepartureDateTime().isBefore(now) && flight.getStatus() != FlightStatus.INACTIVE) {
                flight.setStatus(FlightStatus.INACTIVE);
                flightRepository.save(flight);
            }

            if (flight.getAvailableSeats() == 0 && flight.getStatus() != FlightStatus.INACTIVE) {
                flight.setStatus(FlightStatus.INACTIVE);
                flightRepository.save(flight);
            }

            if (flight.getAvailableSeats() > 0 && flight.getStatus() != FlightStatus.ACTIVE) {
                flight.setStatus(FlightStatus.ACTIVE);
                flightRepository.save(flight);
            }
        }
    }
}
