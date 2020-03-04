package ru.mosolov.robofinance.service.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
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
}
