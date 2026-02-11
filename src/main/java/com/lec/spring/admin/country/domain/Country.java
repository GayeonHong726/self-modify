package com.lec.spring.admin.country.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lec.spring.admin.airport.domain.Airport;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ft_country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(unique = true, name = "country_iso", nullable = false)
    private String countryIso;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<Airport> airports;
}
