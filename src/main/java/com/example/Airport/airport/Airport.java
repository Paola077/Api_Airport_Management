package com.example.Airport.airport;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, length = 3, name = "code", nullable = false)
    private String code;

    @Column(unique = true, length = 255, name = "name", nullable = false)
    private String name;

    @Column(length = 100, name = "location", nullable = false)
    private String location;

    public Airport(String code, String name, String location) {
        this.code = code;
        this.name = name;
        this.location = location;
    }
}
