package com.smartfleet.fleet.controller;

import com.smartfleet.fleet.model.Vehicle;
import com.smartfleet.fleet.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet")
public class VehicleController {

    public final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vehicle> getAll() {
        return service.getAllVehicles();
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return service.addVehicle(vehicle);
    }

}
