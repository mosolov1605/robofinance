package ru.mosolov.robofinance.web;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = AddressController.class)
public class AddressControllerTests {

    private static final String BASE_API_URL = "/customer";

    private String apiPath(String s) {
        return BASE_API_URL + s;
    }
}
