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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "refId",
        "schoolRefId",
        "schoolYear",
        "sessions"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XCalendar extends XWrapper {

    @JsonProperty("refId")
    @XmlElement(name = "refId")
    private String refId;
    @JsonProperty("schoolRefId")
    @XmlElement(name = "schoolRefId")
    private String schoolRefId;
    @JsonProperty("schoolYear")
    @XmlElement(name = "schoolYear")
    private String schoolYear;
    @JsonProperty("sessions")
    @XmlElement(name = "sessions")
    private Sessions sessions;

    public XCalendar() {
    }

    public XCalendar(String refId) {
        this.refId = refId;
    }

    public XCalendar(String refId, String schoolRefId, String schoolYear, Sessions sessions) {
        super();
        this.refId = refId;
        this.schoolRefId = schoolRefId;
        this.schoolYear = schoolYear;
        this.sessions = sessions;
    }



    @JsonProperty("refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("schoolRefId")
    public String getSchoolRefId() {
        return schoolRefId;
    }

    @JsonProperty("schoolRefId")
    public void setSchoolRefId(String schoolRefId) {
        this.schoolRefId = schoolRefId;
    }

    @JsonProperty("schoolYear")
    public String getSchoolYear() {
        return schoolYear;
    }

    @JsonProperty("schoolYear")
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @JsonProperty("sessions")
    public Sessions getSessions() {
        return sessions;
    }

    @JsonProperty("sessions")
    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "XCalendar{" +
                "refId='" + refId + '\'' +
                ", schoolRefId='" + schoolRefId + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", sessions=" + sessions +
                '}';
    }
}