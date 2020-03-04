package ru.mosolov.robofinance.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.mosolov.robofinance.service.CustomerService;
import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerInput;
import ru.mosolov.robofinance.service.dto.CustomerSearch;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/{id}")
    public CustomerInfo getCustomer(@PathVariable final Long id) {
        return customerService.find(id);
    }

    @GetMapping(path = "/list")
    public Page<CustomerInfo> getCustomers(final Pageable pageable){
        return customerService.findAll(pageable);
    }

    @GetMapping(path = "/search")
    public List<CustomerInfo> search(@ModelAttribute @Valid final CustomerSearch search) {
        return customerService.findBySearch(search);
    }

    @PostMapping
    public Long save(@RequestBody @Valid final CustomerInput customer) {
        return customerService.save(customer);
    }

    @PutMapping(path = "/{id}")
    public Long refreshWithPath(@PathVariable Long id, @RequestBody final CustomerInput customer) {
        customer.setId(id);
        return customerService.save(customer);
    }

    @PutMapping
    public Long refresh(@RequestBody final CustomerInput customer) {
        return customerService.save(customer);
    }

    @DeleteMapping(path = "/{id}")
    public Long remove(@PathVariable Long id){
        return customerService.remove(id);
    }
}
