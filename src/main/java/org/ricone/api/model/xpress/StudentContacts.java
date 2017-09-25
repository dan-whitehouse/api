/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.model.xpress;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "contactPersonRefId"
})
public class StudentContacts {

    @JsonProperty("contactPersonRefId")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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