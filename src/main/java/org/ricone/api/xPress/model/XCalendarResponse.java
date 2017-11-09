/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xCalendar"
})
public class XCalendarResponse {

    @JsonProperty("xCalendar")
    private XCalendar xCalendar;

    public XCalendarResponse() {
    }

    public XCalendarResponse(XCalendar xCalendar) {
        super();
        this.xCalendar = xCalendar;
    }

    @JsonProperty("xCalendar")
    public XCalendar getXCalendar() {
        return xCalendar;
    }

    @JsonProperty("xCalendar")
    public void setXCalendar(XCalendar xCalendar) {
        this.xCalendar = xCalendar;
    }

    @Override
    public String toString() {
        return "XCalendarResponse{" +
                "xCalendar=" + xCalendar +
                '}';
    }
}