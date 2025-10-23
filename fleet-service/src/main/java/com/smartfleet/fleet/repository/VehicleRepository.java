package com.smartfleet.fleet.repository;

import com.smartfleet.fleet.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface VehicleRepository extends MongoRepository<Vehicle, String>{
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
}
