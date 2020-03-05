package ru.mosolov.robofinance.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.mosolov.robofinance.domain.dto.AddressSource;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class AddressSearch {

    @NotNull
    String country;
    @NotNull
    String region;
    @NotNull
    String city;
    @NotNull
    String street;
    @NotNull
    String house;
    
    String flat;

    public static AddressSearch applyTo(final AddressSource source) {
        return AddressSearch.builder()
                .country(source.getCountry())
                .region(source.getRegion())
                .city(source.getCity())
                .street(source.getStreet())
                .house(source.getHouse())
                .flat(source.getFlat())
                .build();
    }
}
