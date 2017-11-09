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
        "phoneNumber"
})
public class OtherPhoneNumbers {

    @JsonProperty("phoneNumber")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PhoneNumber> phoneNumber;

    public OtherPhoneNumbers() {
        phoneNumber = new ArrayList<>();
    }

    public OtherPhoneNumbers(List<PhoneNumber> phoneNumber) {
        super();
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public List<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "OtherPhoneNumbers{" +
                "phoneNumber=" + phoneNumber +
                '}';
    }
}