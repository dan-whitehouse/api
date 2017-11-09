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
        "xCalendars"
})
public class XCalendarsResponse {

    @JsonProperty("xCalendars")
    private XCalendars xCalendars;

    public XCalendarsResponse() {
    }

    public XCalendarsResponse(XCalendars xCalendars) {
        super();
        this.xCalendars = xCalendars;
    }

    @JsonProperty("xCalendars")
    public XCalendars getXCalendars() {
        return xCalendars;
    }

    @JsonProperty("xCalendars")
    public void setXCalendars(XCalendars xCalendars) {
        this.xCalendars = xCalendars;
    }

    @Override
    public String toString() {
        return "XCalendarsResponse{" +
                "xCalendars=" + xCalendars +
                '}';
    }
}