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
        "xStaff"
})
public class XStaffResponse {

    @JsonProperty("xStaff")
    private XStaff xStaff;

    public XStaffResponse() {
    }

    public XStaffResponse(XStaff xStaff) {
        super();
        this.xStaff = xStaff;
    }

    @JsonProperty("xStaff")
    public XStaff getXStaff() {
        return xStaff;
    }

    @JsonProperty("xStaff")
    public void setXStaff(XStaff xStaff) {
        this.xStaff = xStaff;
    }

    @Override
    public String toString() {
        return "XStaffResponse{" +
                "xStaff=" + xStaff +
                '}';
    }
}