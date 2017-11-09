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
        "xCourses"
})
public class XCoursesResponse {

    @JsonProperty("xCourses")
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
        return "XCoursesResponse{" +
                "xCourses=" + xCourses +
                '}';
    }
}