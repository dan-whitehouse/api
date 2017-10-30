package org.ricone.api.model.core.extension.userpassword;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EntityTypeConverter implements AttributeConverter<EntityType, String> {

    @Override
    public String convertToDatabaseColumn(EntityType entityType) {
        return entityType.getDbValue();
    }

    @Override
    public EntityType convertToEntityAttribute(String dbData) {
        return EntityType.from(dbData);
    }
}