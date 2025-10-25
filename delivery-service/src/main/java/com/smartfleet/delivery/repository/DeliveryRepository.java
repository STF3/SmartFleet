package com.smartfleet.delivery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.smartfleet.delivery.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
