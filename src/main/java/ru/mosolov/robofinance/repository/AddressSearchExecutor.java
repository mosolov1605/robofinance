package ru.mosolov.robofinance.repository;

import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.service.dto.AddressSearch;

import java.util.List;

public interface AddressSearchExecutor {

    Address findBySearch(AddressSearch search);
}
