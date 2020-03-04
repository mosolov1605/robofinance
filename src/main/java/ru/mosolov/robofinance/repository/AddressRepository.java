package ru.mosolov.robofinance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mosolov.robofinance.domain.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByCountryAndRegionAndCityAndStreetAndHouseAndFlat(String country, String region,
                                                String city, String street, String house, String flat);
}
