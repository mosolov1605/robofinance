package ru.mosolov.robofinance.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import ru.mosolov.robofinance.domain.dto.AddressSource;
import ru.mosolov.robofinance.support.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "address")
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode (callSuper = false)
public class Address extends BaseEntity implements AddressSource {

    @Column
    String country;
    @Column
    String region;
    @Column
    String city;
    @Column
    String street;
    @Column
    String house;
    @Column
    String flat;

    @Column
    @EqualsAndHashCode.Exclude
    final Instant created = Instant.now();
    @Column
    @Builder.Default
    @EqualsAndHashCode.Exclude
    Instant modified = Instant.now();

    public static Address applyTo(AddressSource source) {
        return Address.builder()
                .country(source.getCountry())
                .region(source.getRegion())
                .city(source.getCity())
                .street(source.getStreet())
                .house(source.getHouse())
                .flat(source.getFlat())
                .build();
    }

    public void applyToObject(AddressSource source) {
        this.country = source.getCountry();
        this.region = source.getRegion();
        this.city = source.getCity();
        this.street = source.getStreet();
        this.house = source.getHouse();
        this.flat = source.getFlat();
    }

    @PreUpdate
    public void setLastUpdate() {
        this.modified = Instant.now();
    }
}
