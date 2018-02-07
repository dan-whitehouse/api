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
@JsonPropertyOrder({"xCourses"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XCoursesResponse {

    @JsonProperty("xCourses")
    @XmlElement(name = "xCourses")
    private XCourses xCourses;

    public XCoursesResponse() {
    }

    public XCoursesResponse(XCourses xCourses) {
        super();
        this.xCourses = xCourses;
    }

    @JsonProperty("xCourses")
    public XCourses getXCourses() {
        return xCourses;
    }

    @JsonProperty("xCourses")
    public void setXCourses(XCourses xCourses) {
        this.xCourses = xCourses;
    }

    @Override
    public String toString() {
        return "XCoursesResponse{" + "xCourses=" + xCourses + '}';
    }
}