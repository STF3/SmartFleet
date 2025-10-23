package com.smartfleet.fleet.service;


import com.smartfleet.fleet.model.Vehicle;
import com.smartfleet.fleet.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle>getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehicleByReg(String regNumber) {
        return vehicleRepository.findByRegistrationNumber(regNumber);
    }

}
