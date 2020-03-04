package ru.mosolov.robofinance.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.domain.dto.AddressSource;
import ru.mosolov.robofinance.service.dto.AddressInfo;
import ru.mosolov.robofinance.service.dto.AddressSearch;

public interface AddressService {

    Long save(AddressSource source);
    void saveAll(Iterable<Address> addresses);
    AddressInfo find(Long id);
    Page<AddressInfo> findAll(Pageable pageable);
    AddressInfo findBySearch(AddressSearch search);
    Long remove(Long id);
}
