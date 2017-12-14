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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xSchools"
})
@XmlRootElement
public class XSchoolsResponse {

    @JsonProperty("xSchools")
    @XmlElement(name = "xSchools")
    private XSchools xSchools;

    public XSchoolsResponse() {
    }

    public XSchoolsResponse(XSchools xSchools) {
        super();
        this.xSchools = xSchools;
    }

    @JsonProperty("xSchools")
    public XSchools getXSchools() {
        return xSchools;
    }

    @JsonProperty("xSchools")
    public void setXSchools(XSchools xSchools) {
        this.xSchools = xSchools;
    }

    @Override
    public String toString() {
        return "XSchoolsResponse{" +
                "xSchools=" + xSchools +
                '}';
    }
}