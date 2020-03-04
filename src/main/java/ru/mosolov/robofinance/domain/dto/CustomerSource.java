package ru.mosolov.robofinance.domain.dto;

import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.utils.Gender;

public interface CustomerSource {

    String getFirstName();
    String getLastName();
    String getMiddleName();
    Gender getGender();
    Address getAddress();
    Address getRegAddress();
}
