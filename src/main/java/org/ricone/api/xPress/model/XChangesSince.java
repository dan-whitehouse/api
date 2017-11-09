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

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xChangeSince"
})
public class XChangesSince {

    @JsonProperty("xChangeSince")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<XChangeSince> xChangeSince;

    public XChangesSince() {
        xChangeSince = new ArrayList<>();
    }

    public XChangesSince(List<XChangeSince> xChangeSince) {
        super();
        this.xChangeSince = xChangeSince;
    }

    @JsonProperty("xChangeSince")
    public List<XChangeSince> getXChangeSince() {
        return xChangeSince;
    }

    @JsonProperty("xChangeSince")
    public void setXChangeSince(List<XChangeSince> xChangeSince) {
        this.xChangeSince = xChangeSince;
    }

    @Override
    public String toString() {
        return "xChangesSince{" +
                "xChangeSince=" + xChangeSince +
                '}';
    }
}