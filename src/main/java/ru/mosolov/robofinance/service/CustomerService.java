package ru.mosolov.robofinance.service;

import org.springframework.data.domain.Pageable;
import ru.mosolov.robofinance.domain.dto.CustomerSource;
import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerSearch;

import java.util.List;

public interface CustomerService {

    Long save(CustomerSource customer);
    CustomerInfo find(Long id);
    List<CustomerInfo> findAll(Pageable pageable);
    List<CustomerInfo> findBySearch(CustomerSearch search);
    Long remove(Long id);
}
