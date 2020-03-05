package ru.mosolov.robofinance.repository;

import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerSearch;

import java.util.List;

public interface CustomerSearchExecutor {

    List<CustomerInfo> findBySearch(CustomerSearch search);
}
