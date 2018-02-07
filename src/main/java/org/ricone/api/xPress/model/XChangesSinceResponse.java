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
@JsonPropertyOrder({"xChangesSince"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XChangesSinceResponse {

    @JsonProperty("xChangesSince")
    @XmlElement(name = "xChangesSince")
    private XChangesSince xChangesSince;

    public XChangesSinceResponse() {
    }

    public XChangesSinceResponse(XChangesSince xChangesSince) {
        super();
        this.xChangesSince = xChangesSince;
    }

    @JsonProperty("xChangesSince")
    public XChangesSince getXChangesSince() {
        return xChangesSince;
    }

    @JsonProperty("xChangesSince")
    public void setXChangesSince(XChangesSince xChangesSince) {
        this.xChangesSince = xChangesSince;
    }

    @Override
    public String toString() {
        return "XChangesSinceResponse{" + "xChangesSince=" + xChangesSince + '}';
    }
}