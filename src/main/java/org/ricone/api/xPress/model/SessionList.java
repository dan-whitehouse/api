/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sessionType", "sessionCode", "description", "markingTerm", "schedulingTerm", "linkedSessionCode", "startDate", "endDate"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionList {

    @JsonProperty("sessionType")
    @XmlElement(name = "sessionType")
    private String sessionType;
    @JsonProperty("sessionCode")
    @XmlElement(name = "sessionCode")
    private String sessionCode;
    @JsonProperty("description")
    @XmlElement(name = "description")
    private String description;
    @JsonProperty("markingTerm")
    @XmlElement(name = "markingTerm")
    private String markingTerm;
    @JsonProperty("schedulingTerm")
    @XmlElement(name = "schedulingTerm")
    private String schedulingTerm;
    @JsonProperty("linkedSessionCode")
    @XmlElement(name = "linkedSessionCode")
    private String linkedSessionCode;
    @JsonProperty("startDate")
    @XmlElement(name = "startDate")
    private String startDate;
    @JsonProperty("endDate")
    @XmlElement(name = "endDate")
    private String endDate;

    public SessionList() {
    }

    public SessionList(String sessionType, String sessionCode, String description, String markingTerm, String schedulingTerm, String linkedSessionCode, String startDate, String endDate) {
        super();
        this.sessionType = sessionType;
        this.sessionCode = sessionCode;
        this.description = description;
        this.markingTerm = markingTerm;
        this.schedulingTerm = schedulingTerm;
        this.linkedSessionCode = linkedSessionCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @JsonProperty("sessionType")
    public String getSessionType() {
        return sessionType;
    }

    @JsonProperty("sessionType")
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    @JsonProperty("sessionCode")
    public String getSessionCode() {
        return sessionCode;
    }

    @JsonProperty("sessionCode")
    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("markingTerm")
    public String getMarkingTerm() {
        return markingTerm;
    }

    @JsonProperty("markingTerm")
    public void setMarkingTerm(String markingTerm) {
        this.markingTerm = markingTerm;
    }

    @JsonProperty("schedulingTerm")
    public String getSchedulingTerm() {
        return schedulingTerm;
    }

    @JsonProperty("schedulingTerm")
    public void setSchedulingTerm(String schedulingTerm) {
        this.schedulingTerm = schedulingTerm;
    }

    @JsonProperty("linkedSessionCode")
    public String getLinkedSessionCode() {
        return linkedSessionCode;
    }

    @JsonProperty("linkedSessionCode")
    public void setLinkedSessionCode(String linkedSessionCode) {
        this.linkedSessionCode = linkedSessionCode;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SessionList{" + "sessionType='" + sessionType + '\'' + ", sessionCode='" + sessionCode + '\'' + ", description='" + description + '\'' + ", markingTerm='" + markingTerm + '\'' + ", schedulingTerm='" + schedulingTerm + '\'' + ", linkedSessionCode='" + linkedSessionCode + '\'' + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return Stream.of(sessionType, sessionCode, description, markingTerm, schedulingTerm, linkedSessionCode, startDate, endDate).allMatch(Objects::isNull);
    }
}