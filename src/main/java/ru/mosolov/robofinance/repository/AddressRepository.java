package ru.mosolov.robofinance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mosolov.robofinance.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> , AddressSearchExecutor{

}
