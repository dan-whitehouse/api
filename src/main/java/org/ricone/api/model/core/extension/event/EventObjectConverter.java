package org.ricone.api.model.core.extension.event;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EventObjectConverter implements AttributeConverter<EventObject, String> {

    @Override
    public String convertToDatabaseColumn(EventObject eventType) {
        return eventType.getDbValue();
    }

    @Override
    public EventObject convertToEntityAttribute(String dbData) {
        return EventObject.from(dbData);
    }
}