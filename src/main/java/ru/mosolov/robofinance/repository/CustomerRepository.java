package ru.mosolov.robofinance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mosolov.robofinance.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
