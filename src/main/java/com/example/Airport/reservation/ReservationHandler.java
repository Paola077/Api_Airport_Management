package com.example.Airport.reservation;

import com.example.Airport.flight.Flight;
import com.example.Airport.flight.FlightRepository;
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

    @Scheduled(fixedRate = 60000) // Cada minuto se borran las reservas expiradas
    public void releaseExpiredReservations() {
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expiredReservations = reservationRepository.findAll()
                .stream()
                .filter(r -> r.getReservationExpirationTime().isBefore(now))
                .toList();

        for (Reservation reservation : expiredReservations) {
            Flight flight = reservation.getFlight();
            flight.setAvailableSeats(flight.getAvailableSeats() + reservation.getSeatsReserved());
            flightRepository.save(flight);
            reservationRepository.delete(reservation);

            // TODO se tienen que borrar las reservas que solo tengan el status pending
        }
    }

    @Scheduled(fixedRate = 86400000) //Cada día -> Se limpian las reservas confirmadas de vuelos que ya han salido
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

    //TODO Hacer tarea que confirme que un vuelo esta completo y lo ponga como inactivo y si hay reservas disponibles que ponga activo de nuevo
}
