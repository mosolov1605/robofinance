package ru.mosolov.robofinance.service;

import org.springframework.data.domain.Pageable;
import ru.mosolov.robofinance.service.dto.AddressInfo;
import ru.mosolov.robofinance.service.dto.AddressInput;
import ru.mosolov.robofinance.service.dto.AddressSearch;

import java.util.List;

public interface AddressService {

    void save(AddressInput address);
    AddressInfo find(Long id);
    List<AddressInfo> findAll(Pageable pageable);
    AddressInfo findBySearch(AddressSearch search);
}
