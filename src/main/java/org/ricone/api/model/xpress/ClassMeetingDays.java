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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bellScheduleDay"
})
public class ClassMeetingDays {

    @JsonProperty("bellScheduleDay")
    private String bellScheduleDay;

    public ClassMeetingDays() {
    }

    public ClassMeetingDays(String bellScheduleDay) {
        super();
        this.bellScheduleDay = bellScheduleDay;
    }

    @JsonProperty("bellScheduleDay")
    public String getBellScheduleDay() {
        return bellScheduleDay;
    }

    @JsonProperty("bellScheduleDay")
    public void setBellScheduleDay(String bellScheduleDay) {
        this.bellScheduleDay = bellScheduleDay;
    }

    @Override
    public String toString() {
        return "ClassMeetingDays{" +
                "bellScheduleDay='" + bellScheduleDay + '\'' +
                '}';
    }
}