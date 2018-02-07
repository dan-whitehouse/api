package org.ricone.api.core.model;

import org.apache.commons.lang3.StringUtils;

public enum EventObject {
    CONTACT("Contact"), STUDENT("Student"), STAFF("Staff"), ROSTER("CourseSection"), CALENDAR("Calendar"), COURSE("Course"), SCHOOL("School"), LEA("Lea");

    String dbValue;

    EventObject(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return this.dbValue;
    }

    public static EventObject from(String dbData) {
        if(dbData == null) {
            return null;
        }

        for (EventObject eventObject : EventObject.values()) {
            if(StringUtils.containsIgnoreCase(dbData, eventObject.getDbValue())) {
                return eventObject;
            }
        }
        return null;
    }
}
