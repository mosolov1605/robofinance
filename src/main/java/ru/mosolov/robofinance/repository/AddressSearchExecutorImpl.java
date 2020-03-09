package ru.mosolov.robofinance.repository;

import lombok.RequiredArgsConstructor;
import ru.mosolov.robofinance.domain.Address;
import ru.mosolov.robofinance.service.dto.AddressSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
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
        cq.select(addressRoot);
        filter(cq, addressRoot, cb, search);
        Query query = em.createQuery(cq);
        return query.getResultList().isEmpty() ? null : (Address) query.getResultList().get(0);
    }

    private void filter(CriteriaQuery<Address> cq, Root<Address> addressRoot, CriteriaBuilder cb, AddressSearch search) {
        List<Predicate> predicates = Arrays.asList(
            cb.equal(addressRoot.get(Address.Fields.country), search.getCountry()),
            cb.equal(addressRoot.get(Address.Fields.region), search.getRegion()),
            cb.equal(addressRoot.get(Address.Fields.city), search.getCity()),
            cb.equal(addressRoot.get(Address.Fields.street), search.getStreet()),
            cb.equal(addressRoot.get(Address.Fields.house), search.getHouse())
        );

        if (!Objects.isNull(search.getFlat())) {
            predicates.add(cb.and(cb.equal(addressRoot.get(Address.Fields.flat), search.getFlat())));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
    }
}
