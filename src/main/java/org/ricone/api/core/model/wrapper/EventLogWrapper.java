package org.ricone.api.core.model.wrapper;

import org.ricone.api.core.model.EventLog;

public class EventLogWrapper {
    private String districtId;
    private EventLog eventLog;

    public EventLogWrapper() {
    }

    public EventLogWrapper(String districtId, EventLog eventLog) {
        this.districtId = districtId;
        this.eventLog = eventLog;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public EventLog getEventLog() {
        return eventLog;
    }

    public void setEventLog(EventLog eventLog) {
        this.eventLog = eventLog;
    }
}
