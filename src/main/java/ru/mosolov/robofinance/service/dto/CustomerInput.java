package ru.mosolov.robofinance.service.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.domain.dto.CustomerSource;
import ru.mosolov.robofinance.utils.Gender;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class CustomerInput implements CustomerSource {

    Long id;
    String firstName;
    String lastName;
    String middleName;
    @NotNull
    Gender gender;
    @NotNull
    AddressInput address;
    @NotNull
    AddressInput regAddress;

    @Override
    public Address getAddress() {
        return Address.applyTo(address);
    }

    @Override
    public Address getRegAddress() {
        return Address.applyTo(regAddress);
    }
}
