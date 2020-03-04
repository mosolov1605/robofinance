package ru.mosolov.robofinance.domain.dto;

public interface AddressSource {

     String getCountry();
     String getRegion();
     String getCity();
     String getStreet();
     String getHouse();
     String getFlat();
}
