package ru.mosolov.robofinance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mosolov.robofinance.domain.Address;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByCountryAndRegionAndCityAndStreetAndHouseAndFlat(String country, String region,
                                                String city, String street, String house, String flat);
    Address findByCountryAndRegionAndCityAndStreetAndHouseAndFlatIsNull(String country, String region,
                                                                  String city, String street, String house);
    List<Address> findAll(Pageable pageable);
}
