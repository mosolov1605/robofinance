package ru.mosolov.robofinance.service;

import org.springframework.data.domain.Pageable;
import ru.mosolov.robofinance.domain.Customer;
import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerSearch;
import ru.mosolov.robofinance.service.dto.CustomerInput;

import java.util.List;

public interface CustomerService {

    void save(CustomerInput customer);
    Customer find(Long id);
    List<CustomerInfo> findAll(Pageable pageable);
    List<CustomerInfo> findBySearch(CustomerSearch search);
    void refresh(Long id, CustomerInput customer);
    void remove(Long id);
}
