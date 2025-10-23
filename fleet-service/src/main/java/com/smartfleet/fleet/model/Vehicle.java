package com.smartfleet.fleet.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicles")
public class Vehicle {

    @Id
    private String id;
    private String registrationNumber;
    private String make;
    private String model;
    private String status;
}
