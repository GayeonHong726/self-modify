package com.lec.spring.admin.airport.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //controller,server에서 값을 꺼낼 때 필요
@NoArgsConstructor
public class AirportRequestDto {
    private Integer airportId;
    private String airportName;
    private String airportIso;
    private String countryIso;
    private Double latitudeAirport;
    private Double longitudeAirport;
    private String timezone;
}
