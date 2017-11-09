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
        "xLeas"
})
public class XLeasResponse {

    @JsonProperty("xLeas")
    private XLeas xLeas;

    public XLeasResponse() {
    }

    public XLeasResponse(XLeas xLeas) {
        super();
        this.xLeas = xLeas;
    }

    @JsonProperty("xLeas")
    public XLeas getXLeas() {
        return xLeas;
    }

    @JsonProperty("xLeas")
    public void setXLeas(XLeas xLeas) {
        this.xLeas = xLeas;
    }

    @Override
    public String toString() {
        return "XLeasResponse{" +
                "xLeas=" + xLeas +
                '}';
    }
}