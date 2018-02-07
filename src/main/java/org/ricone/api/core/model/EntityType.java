package org.ricone.api.core.model;

import org.apache.commons.lang3.StringUtils;

public enum EntityType {
    STUDENT("Student"), STAFF("Staff");

    String dbValue;

    EntityType(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return this.dbValue;
    }

    public static EntityType from(String dbData) {
        if(dbData == null) {
            return null;
        }

        for (EntityType eventObject : EntityType.values()) {
            if(StringUtils.equalsIgnoreCase(dbData, eventObject.getDbValue())) {
                return eventObject;
            }
        }
        return null;
    }
}
