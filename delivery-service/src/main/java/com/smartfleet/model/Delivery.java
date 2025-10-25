package com.smartfleet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleId;
    private String driverName;
    private String pickupLocation;
    private String dropLocation;
    private double distanceKm;
    private String status; // CREATED, IN_PROGRESS, COMPLETED
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Delivery() {}

}
