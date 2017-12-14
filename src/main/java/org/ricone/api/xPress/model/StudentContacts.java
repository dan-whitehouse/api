/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "contactPersonRefId"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentContacts {

    @JsonProperty("contactPersonRefId")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "contactPersonRefId")
    private List<String> contactPersonRefId;

    public StudentContacts() {
        contactPersonRefId = new ArrayList<>();
    }

    public StudentContacts(List<String> contactPersonRefId) {
        super();
        this.contactPersonRefId = contactPersonRefId;
    }

    @JsonProperty("contactPersonRefId")
    public List<String> getContactPersonRefId() {
        return contactPersonRefId;
    }

    @JsonProperty("contactPersonRefId")
    public void setContactPersonRefId(List<String> contactPersonRefId) {
        this.contactPersonRefId = contactPersonRefId;
    }

    @Override
    public String toString() {
        return "StudentContacts{" +
                "contactPersonRefId=" + contactPersonRefId +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return contactPersonRefId.isEmpty();
    }
}