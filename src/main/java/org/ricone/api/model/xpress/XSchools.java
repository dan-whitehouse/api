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
        "xSchool"
})
public class XSchools {

    @JsonProperty("xSchool")
    private List<XSchool> xSchool;

    public XSchools() {
        xSchool = new ArrayList<>();
    }

    public XSchools(List<XSchool> xSchool) {
        super();
        this.xSchool = xSchool;
    }

    @JsonProperty("xSchool")
    public List<XSchool> getXSchool() {
        return xSchool;
    }

    @JsonProperty("xSchool")
    public void setXSchool(List<XSchool> xSchool) {
        this.xSchool = xSchool;
    }

    @Override
    public String toString() {
        return "XSchools{" +
                "xSchool=" + xSchool +
                '}';
    }
}