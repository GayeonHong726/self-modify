package com.lec.spring.admin.airport.repository;

import com.lec.spring.admin.airport.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class AirportRepository extends JpaRepository<Airport, Long> {

    @Override
    public List<Airport> findAllById(Iterable<Long> longs) {
        return List.of();
    }
}
