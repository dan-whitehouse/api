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
        "@refId",
        "leaRefId",
        "localId",
        "stateProvinceId",
        "otherIds",
        "schoolName",
        "gradeLevels",
        "address",
        "phoneNumber",
        "otherPhoneNumbers"
})
public class XSchool extends XWrapper {

    @JsonProperty("@refId")
    private String refId;
    @JsonProperty("leaRefId")
    private String leaRefId;
    @JsonProperty("localId")
    private String localId;
    @JsonProperty("stateProvinceId")
    private String stateProvinceId;
    @JsonProperty("otherIds")
    private OtherIds otherIds;
    @JsonProperty("schoolName")
    private String schoolName;
    @JsonProperty("gradeLevels")
    private GradeLevels gradeLevels;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("phoneNumber")
    private PhoneNumber phoneNumber;
    @JsonProperty("otherPhoneNumbers")
    private OtherPhoneNumbers otherPhoneNumbers;


    public XSchool() {
    }

    public XSchool(String refId) {
        this.refId = refId;
    }

    public XSchool(String refId, String leaRefId, String localId, String stateProvinceId, OtherIds otherIds, String schoolName, GradeLevels gradeLevels, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers) {
        super();
        this.refId = refId;
        this.leaRefId = leaRefId;
        this.localId = localId;
        this.stateProvinceId = stateProvinceId;
        this.otherIds = otherIds;
        this.schoolName = schoolName;
        this.gradeLevels = gradeLevels;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.otherPhoneNumbers = otherPhoneNumbers;
    }

    @JsonProperty("@refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("@refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("leaRefId")
    public String getLeaRefId() {
        return leaRefId;
    }

    @JsonProperty("leaRefId")
    public void setLeaRefId(String leaRefId) {
        this.leaRefId = leaRefId;
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

    @JsonProperty("otherIds")
    public OtherIds getOtherIds() {
        return otherIds;
    }

    @JsonProperty("otherIds")
    public void setOtherIds(OtherIds otherIds) {
        this.otherIds = otherIds;
    }

    @JsonProperty("schoolName")
    public String getSchoolName() {
        return schoolName;
    }

    @JsonProperty("schoolName")
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @JsonProperty("gradeLevels")
    public GradeLevels getGradeLevels() {
        return gradeLevels;
    }

    @JsonProperty("gradeLevels")
    public void setGradeLevels(GradeLevels gradeLevels) {
        this.gradeLevels = gradeLevels;
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
        return "XSchool{" +
                "refId='" + refId + '\'' +
                ", leaRefId='" + leaRefId + '\'' +
                ", localId='" + localId + '\'' +
                ", stateProvinceId='" + stateProvinceId + '\'' +
                ", otherIds=" + otherIds +
                ", schoolName='" + schoolName + '\'' +
                ", gradeLevels=" + gradeLevels +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                ", otherPhoneNumbers=" + otherPhoneNumbers +
                '}';
    }
}