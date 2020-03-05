package ru.mosolov.robofinance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.domain.dto.AddressSource;
import ru.mosolov.robofinance.repository.AddressRepository;
import ru.mosolov.robofinance.service.dto.AddressInfo;
import ru.mosolov.robofinance.service.dto.AddressSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public Long save(final AddressSource source) {
        final long[] id = new long[1];
        Optional.of(source)
                .map(AddressSource::getId)
                .flatMap(addressRepository::findById)
                .ifPresentOrElse(d -> {
                    d.applyToObject(source);
                    id[0] = addressRepository.save(d).getId();
                }, () -> id[0] = addressRepository.save(Address.applyTo(source)).getId());
        return id[0];
    }

    @Override
    @Transactional
    public void saveAll(Iterable<Address> addresses) {
        addressRepository.saveAll(addresses);
    }

    @Override
    public AddressInfo find(final Long id) {
        return Optional.of(id)
                .flatMap(addressRepository::findById)
                .map(AddressInfo::applyTo)
                .orElseThrow();
    }

    @Override
    public Page<AddressInfo> findAll(Pageable pageable) {
        return addressRepository.findAll(pageable).map(AddressInfo::applyTo);
    }

    @Override
    public AddressInfo findBySearch(final AddressSearch search) {
        AtomicReference<AddressInfo> info = new AtomicReference<>();
        Optional.of(search).map(AddressSearch::getFlat).ifPresentOrElse(e -> info.set(Optional.of(addressRepository.findByCountryAndRegionAndCityAndStreetAndHouseAndFlat(
                search.getCountry(), search.getRegion(), search.getCity(),
                search.getStreet(), search.getHouse(), search.getFlat()
        )).map(AddressInfo::applyTo).orElseThrow()), () -> info.set(Optional.of(addressRepository.findByCountryAndRegionAndCityAndStreetAndHouseAndFlatIsNull(
                search.getCountry(), search.getRegion(), search.getCity(),
                search.getStreet(), search.getHouse()
        )).map(AddressInfo::applyTo).orElseThrow()));
        return info.get();
    }

    @Override
    @Transactional
    public Long remove(Long id) {
        var address = Optional.of(id).flatMap(addressRepository::findById).orElseThrow();
        addressRepository.delete(address);
        return address.getId();
    }
}
