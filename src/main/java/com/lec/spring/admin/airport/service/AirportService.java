package com.lec.spring.admin.airport.service;

import com.lec.spring.admin.airport.domain.Airport;
import com.lec.spring.admin.airport.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Transactional(readOnly = true)
    public List<Airport> list() {
        return airportRepository.findAll(Sort.by(Sort.Order.asc("airportIso")));
    }

    // 나라별 공항 목록
    @Transactional(readOnly = true)
    public List<Airport> detail(Long countryId) {
        return airportRepository.findByCountry_Id(countryId);
    }

    // 공항 추가
    @Transactional
    public Airport add(Airport airport) {
        return airportRepository.save(airport);
    }

    // 공항 삭제
    @Transactional
    public int delete(String airportIso) {
        Optional<Airport> airport = airportRepository.findByAirportIso(airportIso);

        if (airport.isPresent()) {
            airportRepository.delete(airport.get());
            return 1; // 삭제 성공
        }
        return 0; // 해당 공항 없음
    }
}
