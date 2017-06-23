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

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xCalendar"
})
public class XCalendars {

    @JsonProperty("xCalendar")
    private List<XCalendar> xCalendar;

    public XCalendars() {
        xCalendar = new ArrayList<>();
    }

    public XCalendars(List<XCalendar> xCalendar) {
        super();
        this.xCalendar = xCalendar;
    }

    @JsonProperty("xCalendar")
    public List<XCalendar> getXCalendar() {
        return xCalendar;
    }

    @JsonProperty("xCalendar")
    public void setXCalendar(List<XCalendar> xCalendar) {
        this.xCalendar = xCalendar;
    }

    @Override
    public String toString() {
        return "XCalendars{" +
                "xCalendar=" + xCalendar +
                '}';
    }
}