package com.example.Airport.reservation;

import com.example.Airport.flight.Flight;
import com.example.Airport.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "seats_reserved", nullable = false)
    private Integer seatsReserved;

    @Column(name = "reservation_expiration_time", nullable = false)
    private LocalDateTime reservationExpirationTime;

    @Column(name = "reservation_status", nullable = false)
    private ReservationStatus reservationStatus;

    public Reservation(User user, Flight flight, Integer seatsReserved, LocalDateTime reservationExpirationTime, ReservationStatus reservationStatus) {
        this.user = user;
        this.flight = flight;
        this.seatsReserved = seatsReserved;
        this.reservationExpirationTime = reservationExpirationTime;
        this.reservationStatus = reservationStatus;
    }
}
