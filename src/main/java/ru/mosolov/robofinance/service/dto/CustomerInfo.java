package ru.mosolov.robofinance.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.domain.Customer;
import ru.mosolov.robofinance.utils.Gender;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults (level = PRIVATE)
public class CustomerInfo {

    Long id;
    String firstName;
    String middleName;
    String lastName;
    Gender gender;
    Address address;
    Address regAddress;

    public static CustomerInfo applyTo(final Customer customer) {
        return CustomerInfo.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .gender(customer.getGender())
                .address(customer.getAddress())
                .regAddress(customer.getRegAddress())
                .build();
    }
}
