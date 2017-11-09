package org.ricone.api.core.dao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EventTypeConverter implements AttributeConverter<EventType, String> {

    @Override
    public String convertToDatabaseColumn(EventType eventType) {
        return eventType.getDbValue();
    }

    @Override
    public EventType convertToEntityAttribute(String dbData) {
        return EventType.from(dbData);
    }
}