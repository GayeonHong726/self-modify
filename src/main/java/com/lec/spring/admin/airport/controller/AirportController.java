package com.lec.spring.admin.airport.controller;

import com.lec.spring.admin.airport.domain.Airport;
import com.lec.spring.admin.airport.dto.AirportRequestDto;
import com.lec.spring.admin.airport.dto.AirportResponseDto;
import com.lec.spring.admin.airport.service.AirportService;
import com.lec.spring.admin.country.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/airport")
public class AirportController {
    private final AirportService airportService;

    @Value("${app.api-key.aviation}")
    private String aviation_key;

    @GetMapping("/{codeIataAirport}")
    public ResponseEntity<?> find1(@PathVariable String codeIataAirport){
        URI uri = UriComponentsBuilder
                .fromUriString("https://aviation-edge.com/v2/public/airportDatabase")
                .queryParam("key", aviation_key)
                .queryParam("codeIataAirport", codeIataAirport)
                .build()
                .encode()
                .toUri();

        String result = new RestTemplate().getForObject(uri, String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{codeIataAirport}/{codeIso2Country}")
    public ResponseEntity<?> find2(@PathVariable String codeIataAirport, @PathVariable String codeIso2Country){
        URI uri = UriComponentsBuilder
                .fromUriString("https://aviation-edge.com/v2/public/airportDatabase")
                .queryParam("key", aviation_key)
                .queryParam("codeIataAirport", codeIataAirport)
                .queryParam("codeIso2Country", codeIso2Country)
                .build()
                .encode()
                .toUri();

        String result = new RestTemplate().getForObject(uri, String.class);
        return ResponseEntity.ok(result);
    }

    // 전체 공항 목록
    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<List<AirportResponseDto>> list() {

        List<AirportResponseDto> result = airportService.list()
                .stream()
                .map(AirportResponseDto::from)
                .toList();

        return ResponseEntity.ok(result);
    }

    // 나라별 목록
    @CrossOrigin
    @GetMapping("/detail/{countryId}")
    public ResponseEntity<List<AirportResponseDto>> detail(@PathVariable Long countryId) {

        List<AirportResponseDto> result = airportService.detail(countryId)
                .stream()
                .map(AirportResponseDto::from)
                .toList();

        return ResponseEntity.ok(result);
    }

    // 추가
    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<AirportResponseDto> add(@RequestBody AirportRequestDto dto) {

        Country country = countryService.findByIso(dto.getCountryIso())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 국가 ISO"));

        Airport airport = Airport.create(dto, country);
        Airport saved = airportService.add(airport);

        return new ResponseEntity<>(
                AirportResponseDto.from(saved),
                HttpStatus.CREATED
        );
    }

    // 삭제
    @CrossOrigin
    @DeleteMapping("/delete/{codeIataAirport}")
    public ResponseEntity<?> delete(@PathVariable String codeIataAirport) {
        return new ResponseEntity<>(airportService.delete(codeIataAirport), HttpStatus.OK);
    }
}

