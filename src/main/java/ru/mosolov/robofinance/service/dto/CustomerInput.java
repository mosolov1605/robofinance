package ru.mosolov.robofinance.service.dto;

import lombok.Data;
import ru.mosolov.robofinance.utils.Gender;

@Data
public class CustomerInput {
    String firstName;
    String lastName;
    String middleName;
    Gender gender;
    AddressInput address;
    AddressInput regAddress;
}
