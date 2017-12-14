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
        "xContact"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XContactResponse {

    @JsonProperty("xContact")
    @XmlElement(name = "xContact")
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