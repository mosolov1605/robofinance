package ru.mosolov.robofinance.utils;

public enum Gender {

    MALE, FEMALE;

    public String toDbValue() {
        return this.name().toLowerCase();
    }

    public static Gender from(String gender) {

        return Gender.valueOf(gender.toUpperCase());
    }
}
