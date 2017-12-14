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
        "xSchool"
})
@XmlRootElement
public class XSchoolResponse {

    @JsonProperty("xSchool")
    @XmlElement(name = "xSchool")
    private XSchool xSchool;


    public XSchoolResponse() {
    }

    public XSchoolResponse(XSchool xSchool) {
        super();
        this.xSchool = xSchool;
    }

    @JsonProperty("xSchool")
    public XSchool getXSchool() {
        return xSchool;
    }

    @JsonProperty("xSchool")
    public void setXSchool(XSchool xSchool) {
        this.xSchool = xSchool;
    }

    @Override
    public String toString() {
        return "XSchoolResponse{" +
                "xSchool=" + xSchool +
                '}';
    }
}