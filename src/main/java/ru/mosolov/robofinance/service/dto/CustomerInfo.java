package ru.mosolov.robofinance.service.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.domain.dto.CustomerSource;
import ru.mosolov.robofinance.utils.Gender;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults (level = PRIVATE)
public class CustomerInfo {

    String firstName;
    String lastName;
    String middleName;
    Gender gender;
    Address address;
    Address regAddress;
}
