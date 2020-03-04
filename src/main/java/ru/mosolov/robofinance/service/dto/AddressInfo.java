package ru.mosolov.robofinance.service.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.mosolov.robofinance.domain.dto.AddressSource;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class AddressInfo implements AddressSource {

    String country;
    String region;
    String city;
    String street;
    String house;
    String flat;
}
