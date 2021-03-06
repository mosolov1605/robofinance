package ru.mosolov.robofinance.web;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mosolov.robofinance.domain.dto.CustomerSource;
import ru.mosolov.robofinance.service.CustomerService;
import ru.mosolov.robofinance.service.dto.CustomerInfo;
import ru.mosolov.robofinance.service.dto.CustomerSearch;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTests {

    private static final String BASE_API_URL = "/customer";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Test
    @SneakyThrows
    public void find_shouldReturnResult_whenCustomerIsFound() {
        // given
        CustomerInfo customer = CustomerInfo.builder()
                .id(2L)
                .firstName("Test")
                .middleName("Test")
                .lastName("Test")
                .build();
        when(customerService.find(2L)).thenReturn(customer);

        // expect
        mvc.perform(get(apiPath("/{id}"), 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\":2,\"firstName\":\"Test\",\"middleName\":\"Test\",\"lastName\":\"Test\",\"gender\":null,\"address\":null,\"regAddress\":null}"
                ));
    }

    @Test
    @SneakyThrows
    public void find_shouldReturnList_whenCustomersIsFound() {
        // given
        CustomerInfo customer1 = CustomerInfo.builder()
                .id(1L)
                .firstName("Test")
                .middleName("Test")
                .lastName("Test")
                .build();
        CustomerInfo customer2 = CustomerInfo.builder()
                .id(2L)
                .firstName("Test2")
                .middleName("Test2")
                .lastName("Test2")
                .build();
        when(customerService.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(customer1, customer2)));

        // expect
        mvc.perform(get(apiPath("/list")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"content\":[{\"id\":1,\"firstName\":\"Test\",\"middleName\":\"Test\",\"lastName\":\"Test\",\"gender\":null,\"address\":null,\"regAddress\":null},{\"id\":2,\"firstName\":\"Test\",\"middleName\":\"Test\",\"lastName\":\"Test\",\"gender\":null,\"address\":null,\"regAddress\":null}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,\"totalElements\":2,\"number\":0,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"size\":2,\"numberOfElements\":2,\"first\":true,\"empty\":false}"
                ));
    }

    @Test
    @SneakyThrows
    public void findBySearch_shouldReturnResult_whenCustomersFound() {
        // given
        CustomerInfo customer1 = CustomerInfo.builder()
                .id(1L)
                .firstName("Test")
                .middleName("Test")
                .lastName("Test")
                .build();
        when(customerService.findBySearch(any(CustomerSearch.class)))
                .thenReturn(Collections.singletonList(customer1));

        // expect
        mvc.perform(get(apiPath("/search?firstName={firstName}&lastName={lastName}"), "Test", "Test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":1,\"firstName\":\"Test\",\"middleName\":\"Test\",\"lastName\":\"Test\",\"gender\":null,\"address\":null,\"regAddress\":null}]"
                ));
    }

    @Test
    @SneakyThrows
    public void save_should200_whenSaveIt() {
        // given
        when(customerService.save(any(CustomerSource.class))).thenReturn(0L);

        // expect
        mvc.perform(post(apiPath("/"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"test\", \"lastName\": \"Test\", \"gender\": \"MALE\", \"address\": {\"country\": \"Test\", \"region\": \"Test\", \"city\": \"Test\", \"street\": \"test\", \"house\": \"test\"}, \"regAddress\": {\"country\": \"Test\", \"region\": \"Test\", \"city\": \"Test\", \"street\": \"test\", \"house\": \"test\"}}"))
                .andDo(print())
                .andExpect(status().isOk()
        );
    }

    private String apiPath(String s) {
        return BASE_API_URL + s;
    }

}
