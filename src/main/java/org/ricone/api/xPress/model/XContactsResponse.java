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
        "xContacts"
})
public class XContactsResponse {

    @JsonProperty("xContacts")
    private XContacts xContacts;

    public XContactsResponse() {
    }

    public XContactsResponse(XContacts xContacts) {
        super();
        this.xContacts = xContacts;
    }

    @JsonProperty("xContacts")
    public XContacts getXContacts() {
        return xContacts;
    }

    @JsonProperty("xContacts")
    public void setXContacts(XContacts xContacts) {
        this.xContacts = xContacts;
    }

    @Override
    public String toString() {
        return "XContactsResponse{" +
                "xContacts=" + xContacts +
                '}';
    }
}