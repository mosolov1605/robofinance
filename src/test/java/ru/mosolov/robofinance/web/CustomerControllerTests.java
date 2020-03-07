package ru.mosolov.robofinance.web;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.mosolov.robofinance.service.CustomerService;
import ru.mosolov.robofinance.service.dto.CustomerInfo;

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

        CustomerInfo customerInfo = CustomerInfo.builder()
                .id(2L)
                .firstName("Test")
                .middleName("Test")
                .lastName("Test")
                .build();
        when(customerService.find(2L)).thenReturn(customerInfo);

        // expect
        mvc.perform(get(apiPath("/{id}"), 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"id\":2,\"firstName\":\"Test\",\"middleName\":\"Test\",\"lastName\":\"Test\",\"gender\":null,\"address\":null,\"regAddress\":null}"
                ));
    }

    private String apiPath(String s) {
        return BASE_API_URL + s;
    }

}
