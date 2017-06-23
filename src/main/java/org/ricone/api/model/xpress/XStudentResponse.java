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
        "xStudent"
})
public class XStudentResponse {

    @JsonProperty("xStudent")
    private XStudent xStudent;

    public XStudentResponse() {
    }

    public XStudentResponse(XStudent xStudent) {
        super();
        this.xStudent = xStudent;
    }

    @JsonProperty("xStudent")
    public XStudent getXStudent() {
        return xStudent;
    }

    @JsonProperty("xStudent")
    public void setXStudent(XStudent xStudent) {
        this.xStudent = xStudent;
    }

    @Override
    public String toString() {
        return "XStudentResponse{" +
                "xStudent=" + xStudent +
                '}';
    }
}