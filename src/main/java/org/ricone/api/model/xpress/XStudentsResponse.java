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
        "xStudents"
})
public class XStudentsResponse {

    @JsonProperty("xStudents")
    private XStudents xStudents;

    public XStudentsResponse() {
    }

    public XStudentsResponse(XStudents xStudents) {
        super();
        this.xStudents = xStudents;
    }

    @JsonProperty("xStudents")
    public XStudents getXStudents() {
        return xStudents;
    }

    @JsonProperty("xStudents")
    public void setXStudents(XStudents xStudents) {
        this.xStudents = xStudents;
    }

    @Override
    public String toString() {
        return "XStudentsResponse{" +
                "xStudents=" + xStudents +
                '}';
    }
}