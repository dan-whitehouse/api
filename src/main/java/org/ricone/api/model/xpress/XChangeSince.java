/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.model.xpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonPropertyOrder({
        "@refId",
        "eventType",
        "eventDate"
})
public class XChangeSince {

    @JsonProperty("@refId")
    private String refId;
    @JsonProperty("eventType")
    private String eventType;
    @JsonProperty("eventDate")
    private String eventDate;

    public XChangeSince() {
    }

    public XChangeSince(String refId, String eventType, String eventDate) {
        super();
        this.refId = refId;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }

    @JsonProperty("@refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("@refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("eventType")
    public String getEventType() {
        return eventType;
    }

    @JsonProperty("eventType")
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @JsonProperty("eventDate")
    public String getEventDate() {
        return eventDate;
    }

    @JsonProperty("eventDate")
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }


    @Override
    public String toString() {
        return "XChangeSince{" +
                "refId='" + refId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}