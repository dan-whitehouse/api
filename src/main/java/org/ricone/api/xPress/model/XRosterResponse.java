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
        "xRoster"
})
public class XRosterResponse {

    @JsonProperty("xRoster")
    private XRoster xRoster;

    public XRosterResponse() {
    }

    public XRosterResponse(XRoster xRoster) {
        super();
        this.xRoster = xRoster;
    }

    @JsonProperty("xRoster")
    public XRoster getXRoster() {
        return xRoster;
    }

    @JsonProperty("xRoster")
    public void setXRoster(XRoster xRoster) {
        this.xRoster = xRoster;
    }

    @Override
    public String toString() {
        return "XRosterResponse{" +
                "xRoster=" + xRoster +
                '}';
    }
}