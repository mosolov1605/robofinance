package ru.mosolov.robofinance.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import ru.mosolov.robofinance.domain.dto.CustomerSource;
import ru.mosolov.robofinance.support.BaseEntity;
import ru.mosolov.robofinance.support.GenderConverter;
import ru.mosolov.robofinance.utils.Gender;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "customer")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class Customer extends BaseEntity implements CustomerSource {

    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String middleName;

    @Convert(converter = GenderConverter.class)
    @Column (name = "sex", nullable = false)
    Gender gender;

    @ManyToOne
    @JoinColumn(name = "actual_address_id", nullable = false)
    Address address;
    @ManyToOne
    @JoinColumn(name = "registered_address_id", nullable = false)
    Address regAddress;

    public static Customer applyTo(final CustomerSource source, Address address, Address regAddress) {
        var customer = applyTo(source);
        customer.applyAddressObject(address, regAddress);
        return customer;
    }

    public static Customer applyTo(final CustomerSource source) {
        return Customer.builder()
                .firstName(source.getFirstName())
                .middleName(source.getMiddleName())
                .lastName(source.getLastName())
                .gender(source.getGender())
                .address(source.getAddress())
                .regAddress(source.getRegAddress())
                .build();
    }

    public void applyToObject(final CustomerSource source) {
        this.firstName = source.getFirstName();
        this.middleName = source.getMiddleName();
        this.lastName = source.getLastName();
        this.gender = source.getGender();
        this.address = source.getAddress();
        this.regAddress = source.getRegAddress();
    }

    public void applyAddressObject(Address address, Address regAddress) {
        this.address = address;
        this.regAddress = regAddress;
    }
}
