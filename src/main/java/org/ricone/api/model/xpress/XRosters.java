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

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xRoster"
})
public class XRosters {

    @JsonProperty("xRoster")
    private List<XRoster> xRoster;

    public XRosters() {
        xRoster = new ArrayList<>();
    }

    public XRosters(List<XRoster> xRoster) {
        super();
        this.xRoster = xRoster;
    }

    @JsonProperty("xRoster")
    public List<XRoster> getXRoster() {
        return xRoster;
    }

    @JsonProperty("xRoster")
    public void setXRoster(List<XRoster> xRoster) {
        this.xRoster = xRoster;
    }

    @Override
    public String toString() {
        return "XRosters{" +
                "xRoster=" + xRoster +
                '}';
    }
}