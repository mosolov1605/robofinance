package ru.mosolov.robofinance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.domain.Customer;
import ru.mosolov.robofinance.domain.dto.CustomerSource;
import ru.mosolov.robofinance.repository.AddressRepository;
import ru.mosolov.robofinance.repository.CustomerRepository;
import ru.mosolov.robofinance.service.dto.AddressSearch;
import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerSearch;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
//    private final AddressService addressService;

    @Override
    @Transactional
    public Long save(CustomerSource source) {
        final long[] id = new long[1];
        AtomicReference<Address> addressAtomicReference = new AtomicReference<>();
        AtomicReference<Address> regAddressAtomicReference = new AtomicReference<>();
        resolveAddress(source, addressAtomicReference, CustomerSource::getAddress);
        resolveAddress(source, regAddressAtomicReference, CustomerSource::getRegAddress);
        var address = addressAtomicReference.get();
        var regAddress = regAddressAtomicReference.get();
        addressRepository.saveAll(List.of(address, regAddress));
        var customer = Customer.applyTo(source, address, regAddress);
        Optional.of(customer)
                .map(CustomerSource::getId)
                .flatMap(customerRepository::findById)
                .ifPresentOrElse(d -> {
                            d.applyToObject(customer);
                            id[0] = customerRepository.save(d).getId();
                        }
                        , () -> id[0] = customerRepository.save(customer).getId());
        return id[0];
    }

    @Override
    public CustomerInfo find(Long id) {
        return customerRepository.findById(id).map(CustomerInfo::applyTo).orElseThrow();
    }

    @Override
    public Page<CustomerInfo> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).map(CustomerInfo::applyTo);
    }

    @Override
    public List<CustomerInfo> findBySearch(CustomerSearch search) {
        return customerRepository.findBySearch(search);
    }

    @Override
    @Transactional
    public Long remove(Long id) {
        var customer = Optional.of(id).flatMap(customerRepository::findById).orElseThrow();
        customerRepository.delete(customer);
        return customer.getId();
    }

    private void resolveAddress(CustomerSource source, AtomicReference<Address> address,
                                Function<CustomerSource, Address> function) {
        final boolean[] exist = new boolean[1];
        Optional.of(source)
                .map(function)
                .map(Address::getId)
                .ifPresent(id -> {
                    Optional.of(addressRepository.findById(id))
                            .ifPresentOrElse(d -> {
                                address.set(d.get());
                                exist[0] = true;
                            }, () -> {
                                Optional.of(addressRepository.findBySearch(AddressSearch.applyTo(function.apply(source))))
                                        .ifPresent(s -> {
                                            address.set(s);
                                            exist[0] = true;
                                        });
                            });
                });

        if (exist[0]) {
            return;
        }
        address.set(Address.applyTo(function.apply(source)));
    }
}
