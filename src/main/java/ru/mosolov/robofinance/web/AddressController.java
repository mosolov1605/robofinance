package ru.mosolov.robofinance.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.mosolov.robofinance.service.AddressService;
import ru.mosolov.robofinance.service.dto.AddressInfo;
import ru.mosolov.robofinance.service.dto.AddressInput;
import ru.mosolov.robofinance.service.dto.AddressSearch;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping(path = "/{id}")
    public AddressInfo getAddress(@PathVariable final Long id) {
        return addressService.find(id);
    }

    @GetMapping(path = "/list")
    public Page<AddressInfo> getAddresses(final Pageable pageable){
        return addressService.findAll(pageable);
    }

    @GetMapping(path = "/search")
    public AddressInfo search(@ModelAttribute @Valid final AddressSearch search) {
        return addressService.findBySearch(search);
    }

    @PostMapping
    public Long save(@RequestBody @Valid final AddressInput address) {
        return addressService.save(address);
    }

    @PutMapping(path = "/{id}")
    public Long refreshWithPath(@PathVariable Long id, @RequestBody final AddressInput address) {
        address.setId(id);
        return addressService.save(address);
    }

    @PutMapping
    public Long refresh(@RequestBody final AddressInput address) {
        return addressService.save(address);
    }

    @DeleteMapping(path = "/{id}")
    public Long remove(@PathVariable Long id){
        return addressService.remove(id);
    }
}
