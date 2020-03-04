package ru.mosolov.robofinance.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.mosolov.robofinance.domain.Address;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AddressInfo {

    Long id;
    String country;
    String region;
    String city;
    String street;
    String house;
    String flat;

    public static AddressInfo applyTo(final Address address) {
        return AddressInfo.builder()
                .country(address.getCountry())
                .region(address.getRegion())
                .city(address.getCity())
                .build();
    }
}
