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
        "@refId",
        "localId",
        "stateProvinceId",
        "ncesId",
        "leaName",
        "address",
        "phoneNumber",
        "otherPhoneNumbers"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XLea extends XWrapper{

    @JsonProperty("refId")
    @XmlElement(name = "refId")
    private String refId;
    @JsonProperty("localId")
    @XmlElement(name = "localId")
    private String localId;
    @JsonProperty("stateProvinceId")
    @XmlElement(name = "stateProvinceId")
    private String stateProvinceId;
    @JsonProperty("ncesId")
    @XmlElement(name = "ncesId")
    private String ncesId;
    @JsonProperty("leaName")
    @XmlElement(name = "leaName")
    private String leaName;
    @JsonProperty("address")
    @XmlElement(name = "address")
    private Address address;
    @JsonProperty("phoneNumber")
    @XmlElement(name = "phoneNumber")
    private PhoneNumber phoneNumber;
    @JsonProperty("otherPhoneNumbers")
    @XmlElement(name = "otherPhoneNumbers")
    private OtherPhoneNumbers otherPhoneNumbers;

    public XLea() {
    }

    public XLea(String refId) {
        this.refId = refId;
    }

    public XLea(String refId, String localId, String stateProvinceId, String ncesId, String leaName, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers) {
        super();
        this.refId = refId;
        this.localId = localId;
        this.stateProvinceId = stateProvinceId;
        this.ncesId = ncesId;
        this.leaName = leaName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.otherPhoneNumbers = otherPhoneNumbers;
    }

    @JsonProperty("refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("localId")
    public String getLocalId() {
        return localId;
    }

    @JsonProperty("localId")
    public void setLocalId(String localId) {
        this.localId = localId;
    }

    @JsonProperty("stateProvinceId")
    public String getStateProvinceId() {
        return stateProvinceId;
    }

    @JsonProperty("stateProvinceId")
    public void setStateProvinceId(String stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    @JsonProperty("ncesId")
    public String getNcesId() {
        return ncesId;
    }

    @JsonProperty("ncesId")
    public void setNcesId(String ncesId) {
        this.ncesId = ncesId;
    }

    @JsonProperty("leaName")
    public String getLeaName() {
        return leaName;
    }

    @JsonProperty("leaName")
    public void setLeaName(String leaName) {
        this.leaName = leaName;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("phoneNumber")
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("otherPhoneNumbers")
    public OtherPhoneNumbers getOtherPhoneNumbers() {
        return otherPhoneNumbers;
    }

    @JsonProperty("otherPhoneNumbers")
    public void setOtherPhoneNumbers(OtherPhoneNumbers otherPhoneNumbers) {
        this.otherPhoneNumbers = otherPhoneNumbers;
    }

    @Override
    public String toString() {
        return "XLea{" +
                "refId='" + refId + '\'' +
                ", localId='" + localId + '\'' +
                ", stateProvinceId='" + stateProvinceId + '\'' +
                ", ncesId='" + ncesId + '\'' +
                ", leaName='" + leaName + '\'' +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                ", otherPhoneNumbers=" + otherPhoneNumbers +
                '}';
    }
}