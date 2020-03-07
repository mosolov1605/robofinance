package ru.mosolov.robofinance.service.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.mosolov.robofinance.domain.dto.AddressSource;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class AddressInput implements AddressSource {

    Long id;
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
}
