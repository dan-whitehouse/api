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
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xContact"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XContacts {

    @JsonProperty("xContact")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "xContact")
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
        return "XContacts{" + "xContact=" + xContact + '}';
    }
}