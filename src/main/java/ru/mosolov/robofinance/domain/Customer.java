package ru.mosolov.robofinance.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.mosolov.robofinance.utils.Gender;
import ru.mosolov.robofinance.support.BaseEntity;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "customer")
@FieldDefaults(level = PRIVATE)
public class Customer extends BaseEntity {

    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String middleName;
    @Column (name = "sex", nullable = false)
    Gender gender;

    @ManyToOne
    @JoinColumn(name = "actual_address_id", nullable = false)
    Address address;
    @ManyToOne
    @JoinColumn(name = "registered_address_id", nullable = false)
    Address regAddress;
}
