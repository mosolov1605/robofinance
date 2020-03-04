package ru.mosolov.robofinance.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mosolov.robofinance.domain.dto.CustomerSource;
import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerSearch;

import java.util.List;

public interface CustomerService {

    Long save(CustomerSource source);
    CustomerInfo find(Long id);
    Page<CustomerInfo> findAll(Pageable pageable);
    List<CustomerInfo> findBySearch(CustomerSearch search);
    Long remove(Long id);
}
