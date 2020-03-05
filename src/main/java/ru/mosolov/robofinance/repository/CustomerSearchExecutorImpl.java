package ru.mosolov.robofinance.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import ru.mosolov.robofinance.domain.Customer;
import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
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

        return null;
    }

    private void filter(CriteriaQuery<?> query, Root<Customer> customerRoot,
                        CriteriaBuilder cb,
                        CustomerSearch criteria) {

        if (!StringUtils.isEmpty(criteria.getFirstName())) {
            cb.and(cb.equal(customerRoot.get(Customer.Fields.firstName), criteria.getFirstName()));
        }
        if (!StringUtils.isEmpty(criteria.getMiddleName())) {
            cb.and(cb.equal(customerRoot.get(Customer.Fields.), criteria.getFirstName()));
        }
        query.where(predicates);
    }
}
