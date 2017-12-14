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
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "addressType",
        "line1",
        "line2",
        "city",
        "stateProvince",
        "countryCode",
        "postalCode"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    @JsonProperty("addressType")
    @XmlElement(name = "addressType")
    private String addressType;
    @JsonProperty("line1")
    @XmlElement(name = "line1")
    private String line1;
    @JsonProperty("line2")
    @XmlElement(name = "line2")
    private String line2;
    @JsonProperty("city")
    @XmlElement(name = "city")
    private String city;
    @JsonProperty("stateProvince")
    @XmlElement(name = "stateProvince")
    private String stateProvince;
    @JsonProperty("countryCode")
    @XmlElement(name = "countryCode")
    private String countryCode;
    @JsonProperty("postalCode")
    @XmlElement(name = "postalCode")
    private String postalCode;

    public Address() {
    }

    public Address(String addressType, String line1, String line2, String city, String stateProvince, String countryCode, String postalCode) {
        super();
        this.addressType = addressType;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.stateProvince = stateProvince;
        this.countryCode = countryCode;
        this.postalCode = postalCode;
    }

    @JsonProperty("addressType")
    public String getAddressType() {
        return addressType;
    }

    @JsonProperty("addressType")
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @JsonProperty("line1")
    public String getLine1() {
        return line1;
    }

    @JsonProperty("line1")
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    @JsonProperty("line2")
    public String getLine2() {
        return line2;
    }

    @JsonProperty("line2")
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("stateProvince")
    public String getStateProvince() {
        return stateProvince;
    }

    @JsonProperty("stateProvince")
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressType='" + addressType + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(addressType, line1, line2, city, stateProvince, countryCode, postalCode).allMatch(Objects::isNull);
    }
}