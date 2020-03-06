package ru.mosolov.robofinance.repository;

import lombok.RequiredArgsConstructor;
import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.service.dto.AddressSearch;
import ru.mosolov.robofinance.support.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class AddressSearchExecutorImpl implements AddressSearchExecutor {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Address findBySearch(AddressSearch search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Address> cq = cb.createQuery(Address.class);
        Root<Address> addressRoot = cq.from(Address.class);
        filter(cq, addressRoot, cb, search);
        cq.distinct(true).select(cb.construct(
                Address.class,
                addressRoot.get(BaseEntity.Fields.id)
        ));
        TypedQuery<Address> typedQuery = em.createQuery(cq);
        return typedQuery.getSingleResult();
    }

    private void filter(CriteriaQuery<Address> cq, Root<Address> addressRoot, CriteriaBuilder cb, AddressSearch search) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.and(cb.equal(addressRoot.get(Address.Fields.country), search.getCountry()),
                cb.equal(addressRoot.get(Address.Fields.region), search.getRegion()),
                cb.equal(addressRoot.get(Address.Fields.city), search.getCity()),
                cb.equal(addressRoot.get(Address.Fields.street), search.getStreet()),
                cb.equal(addressRoot.get(Address.Fields.house), search.getRegion())
        ));
        if (!Objects.isNull(search.getFlat())) {
            predicates.add(cb.and(cb.equal(addressRoot.get(Address.Fields.flat), search.getFlat())));
        }
        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
    }
}
