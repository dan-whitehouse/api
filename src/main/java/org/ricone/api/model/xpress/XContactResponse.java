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

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xContact"
})
public class XContactResponse {

    @JsonProperty("xContact")
    private XContact xContact;

    public XContactResponse() {
    }

    public XContactResponse(XContact xContact) {
        super();
        this.xContact = xContact;
    }

    @JsonProperty("xContact")
    public XContact getXContact() {
        return xContact;
    }

    @JsonProperty("xContact")
    public void setXContact(XContact xContact) {
        this.xContact = xContact;
    }

    @Override
    public String toString() {
        return "XContactResponse{" +
                "xContact=" + xContact +
                '}';
    }
}