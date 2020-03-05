package ru.mosolov.robofinance.support;

import ru.mosolov.robofinance.utils.Gender;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute.toDbValue();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.from(dbData);
    }
}
