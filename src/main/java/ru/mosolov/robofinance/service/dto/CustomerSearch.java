package ru.mosolov.robofinance.service.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class CustomerSearch {

    @NotNull
    String firstName;
    String middleName;
    @NotNull
    String lastName;
}
