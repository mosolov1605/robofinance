package ru.mosolov.robofinance.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
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
@RequiredArgsConstructor
@Table(name = "address")
@FieldDefaults(level = PRIVATE)
public class Address extends BaseEntity {

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
    Instant created = Instant.now();
    @Column
    Instant modified = Instant.now();

    @PreUpdate
    public void setLastUpdate() {
        this.modified = Instant.now();
    }
}
