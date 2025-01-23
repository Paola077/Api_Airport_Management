package com.example.Airport.flight;

import com.example.Airport.airport.Airport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Airport destination;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private Integer totalSeats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;

    public Flight(LocalDate date, LocalTime time, Integer availableSeats, Integer totalSeats, String status) {
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
        this.totalSeats = totalSeats;
    }
}
