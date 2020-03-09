package ru.mosolov.robofinance.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import ru.mosolov.robofinance.domain.Customer;
import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerSearch;
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

@RequiredArgsConstructor
public class CustomerSearchExecutorImpl implements CustomerSearchExecutor {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<CustomerInfo> findBySearch(final CustomerSearch search) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomerInfo> cq = cb.createQuery(CustomerInfo.class);
        Root<Customer> customerRoot = cq.from(Customer.class);
        filter(cq, customerRoot, cb, search);
        cq.distinct(true).select(cb.construct(
                CustomerInfo.class,
                customerRoot.get(BaseEntity.Fields.id),
                customerRoot.get(Customer.Fields.firstName),
                customerRoot.get(Customer.Fields.middleName),
                customerRoot.get(Customer.Fields.lastName),
                customerRoot.get(Customer.Fields.gender),
                customerRoot.get(Customer.Fields.address),
                customerRoot.get(Customer.Fields.regAddress)
        ));
        TypedQuery<CustomerInfo> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    private void filter(CriteriaQuery<?> query, Root<Customer> customerRoot,
                        CriteriaBuilder cb,
                        CustomerSearch search) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(search.getFirstName())) {
            predicates.add(cb.and(cb.equal(customerRoot.get(Customer.Fields.firstName), search.getFirstName())));
        }
        if (!StringUtils.isEmpty(search.getMiddleName())) {
            predicates.add(cb.and(cb.equal(customerRoot.get(Customer.Fields.middleName), search.getMiddleName())));
        }
        if (!StringUtils.isEmpty(search.getLastName())) {
            predicates.add(cb.and(cb.equal(customerRoot.get(Customer.Fields.lastName), search.getLastName())));
        }
        query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
    }
}
