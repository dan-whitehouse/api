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
        "xContact"
})
public class XContacts {

    @JsonProperty("xContact")
    private List<XContact> xContact;

    public XContacts() {
        xContact = new ArrayList<>();
    }

    public XContacts(List<XContact> xContact) {
        super();
        this.xContact = xContact;
    }

    @JsonProperty("xContact")
    public List<XContact> getXContact() {
        return xContact;
    }

    @JsonProperty("xContact")
    public void setXContact(List<XContact> xContact) {
        this.xContact = xContact;
    }

    @Override
    public String toString() {
        return "XContacts{" +
                "xContact=" + xContact +
                '}';
    }
}