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
        "xRosters"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XRostersResponse {

    @JsonProperty("xRosters")
    @XmlElement(name = "xRosters")
    private XRosters xRosters;

    public XRostersResponse() {
    }

    public XRostersResponse(XRosters xRosters) {
        super();
        this.xRosters = xRosters;
    }

    @JsonProperty("xRosters")
    public XRosters getXRosters() {
        return xRosters;
    }

    @JsonProperty("xRosters")
    public void setXRosters(XRosters xRosters) {
        this.xRosters = xRosters;
    }

    @Override
    public String toString() {
        return "XRostersResponse{" +
                "xRosters=" + xRosters +
                '}';
    }
}