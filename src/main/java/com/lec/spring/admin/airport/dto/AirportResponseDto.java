package com.lec.spring.admin.airport.dto;

import com.lec.spring.admin.airport.domain.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AirportResponseDto {
    private Long id;
    private String airportName;
    private String airportIso;
    private String countryIso;
    private String timezone;

    public static AirportResponseDto from(Airport airport) {
        return new AirportResponseDto(
                airport.getId(),
                airport.getAirportName(),
                airport.getAirportIso(),
                airport.getCountryIso(),
                airport.getTimezone()
        );
    }

}
