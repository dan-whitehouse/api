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
        "xCourse"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XCourseResponse {

    @JsonProperty("xCourse")
    @XmlElement(name = "xCourse")
    private XCourse xCourse;

    public XCourseResponse() {
    }

    public XCourseResponse(XCourse xCourse) {
        super();
        this.xCourse = xCourse;
    }

    @JsonProperty("xCourse")
    public XCourse getXCourse() {
        return xCourse;
    }

    @JsonProperty("xCourse")
    public void setXCourse(XCourse xCourse) {
        this.xCourse = xCourse;
    }

    @Override
    public String toString() {
        return "XCourseResponse{" +
                "xCourse=" + xCourse +
                '}';
    }
}