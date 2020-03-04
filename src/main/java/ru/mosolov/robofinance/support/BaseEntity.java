package ru.mosolov.robofinance.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public abstract class BaseEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postgres_sequence")
    @SequenceGenerator(name = "postgres_sequence", allocationSize = 1)
    Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        BaseEntity other = (BaseEntity) o;
        return null != id && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
