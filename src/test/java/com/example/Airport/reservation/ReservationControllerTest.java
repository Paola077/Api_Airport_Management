package com.example.Airport.reservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
class ReservationControllerTest {

    @Test
    void shouldCreateReservationSuccessfully() {
        // Datos de entrada
        Long userId = 1L;
        Long flightId = 1L;
        Integer seatsReserved = 2;
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(15);

        ReservationRequest request = new ReservationRequest(userId, flightId, seatsReserved);

        // Mock del vuelo
        Flight flight = new Flight();
        flight.setId(flightId);
        flight.setAvailableSeats(10);
        flight.setTotalSeats(10);

        // Mock del usuario
        User user = new User();
        user.setId(userId);

        // Mock de la reserva
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setFlight(flight);
        reservation.setSeatsReserved(seatsReserved);
        reservation.setReservationExpirationTime(expirationTime);

        // Simular repositorios
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Ejecutar la lógica
        ReservationResponse response = reservationService.createReservation(request);

        // Validar resultados
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("John Doe", response.userName());
        assertEquals(2, response.seatsReserved());
        assertTrue(response.reservationExpirationTime().isAfter(LocalDateTime.now()));
        verify(flightRepository, times(1)).save(flight);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void shouldDoNothingWhenNoReservationsAreExpired() {
        // Datos simulados
        LocalDateTime now = LocalDateTime.now();
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setAvailableSeats(8);
        flight.setTotalSeats(10);

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setFlight(flight);
        reservation.setSeatsReserved(2);
        reservation.setReservationExpirationTime(now.plusMinutes(10)); // Reserva activa

        // Mock del repositorio
        when(reservationRepository.findAll()).thenReturn(List.of(reservation));

        // Ejecutar la lógica
        reservationCleaner.releaseExpiredReservations();

        // Validar que no se hicieron cambios
        assertEquals(8, flight.getAvailableSeats()); // Sin cambios
        verify(flightRepository, never()).save(any());
        verify(reservationRepository, never()).delete(any());
    }
}*/
