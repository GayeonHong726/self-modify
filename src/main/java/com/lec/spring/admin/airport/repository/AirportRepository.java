package com.lec.spring.admin.airport.repository;

import com.lec.spring.admin.airport.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByAirportIso(String airportIso);

    List<Airport> findByCountry_Id(Long countryId);
}
