package org.ricone.api.model.core.extension;

public enum EventType {
    INSERT("i"),
    UPDATE("u"),
    DELETE("d");

    String dbValue;

    EventType(String dbValue) {
        this.dbValue = dbValue;
    }
    public String getDbValue() { return this.dbValue; }

    public static EventType from(String dbData) {
        if (dbData == null) {
            return null;
        }

        for (EventType eventType : EventType.values()) {
            if (dbData.equalsIgnoreCase(eventType.getDbValue())) {
                return eventType;
            }
        }
        return null;
    }
}
