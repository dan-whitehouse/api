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
        "name",
        "otherNames",
        "localId",
        "otherIds",
        "address",
        "phoneNumber",
        "otherPhoneNumbers",
        "email",
        "otherEmails",
        "sex",
        "employerType",
        "relationships"
})
public class XContact extends XWrapper {

    @JsonProperty("@refId")
    private String refId;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("otherNames")
    private OtherNames otherNames;
    @JsonProperty("localId")
    private String localId;
    @JsonProperty("otherIds")
    private OtherIds otherIds;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("phoneNumber")
    private PhoneNumber phoneNumber;
    @JsonProperty("otherPhoneNumbers")
    private OtherPhoneNumbers otherPhoneNumbers;
    @JsonProperty("email")
    private Email email;
    @JsonProperty("otherEmails")
    private OtherEmails otherEmails;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("employerType")
    private String employerType;
    @JsonProperty("relationships")
    private Relationships relationships;

    public XContact() {
    }

    public XContact(String refId) {
        this.refId = refId;
    }

    public XContact(String refId, Name name, OtherNames otherNames, String localId, OtherIds otherIds, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Email email, OtherEmails otherEmails, String sex, String employerType, Relationships relationships) {
        super();
        this.refId = refId;
        this.name = name;
        this.otherNames = otherNames;
        this.localId = localId;
        this.otherIds = otherIds;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.otherPhoneNumbers = otherPhoneNumbers;
        this.email = email;
        this.otherEmails = otherEmails;
        this.sex = sex;
        this.employerType = employerType;
        this.relationships = relationships;
    }

    @JsonProperty("@refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("@refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    @JsonProperty("otherNames")
    public OtherNames getOtherNames() {
        return otherNames;
    }

    @JsonProperty("otherNames")
    public void setOtherNames(OtherNames otherNames) {
        this.otherNames = otherNames;
    }

    @JsonProperty("localId")
    public String getLocalId() {
        return localId;
    }

    @JsonProperty("localId")
    public void setLocalId(String localId) {
        this.localId = localId;
    }

    @JsonProperty("otherIds")
    public OtherIds getOtherIds() {
        return otherIds;
    }

    @JsonProperty("otherIds")
    public void setOtherIds(OtherIds otherIds) {
        this.otherIds = otherIds;
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

    @JsonProperty("email")
    public Email getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(Email email) {
        this.email = email;
    }

    @JsonProperty("otherEmails")
    public OtherEmails getOtherEmails() {
        return otherEmails;
    }

    @JsonProperty("otherEmails")
    public void setOtherEmails(OtherEmails otherEmails) {
        this.otherEmails = otherEmails;
    }

    @JsonProperty("sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonProperty("employerType")
    public String getEmployerType() {
        return employerType;
    }

    @JsonProperty("employerType")
    public void setEmployerType(String employerType) {
        this.employerType = employerType;
    }

    @JsonProperty("relationships")
    public Relationships getRelationships() {
        return relationships;
    }

    @JsonProperty("relationships")
    public void setRelationships(Relationships relationships) {
        this.relationships = relationships;
    }

    @Override
    public String toString() {
        return "XContact{" +
                "refId='" + refId + '\'' +
                ", name=" + name +
                ", otherNames=" + otherNames +
                ", localId='" + localId + '\'' +
                ", otherIds=" + otherIds +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                ", otherPhoneNumbers=" + otherPhoneNumbers +
                ", email=" + email +
                ", otherEmails=" + otherEmails +
                ", sex='" + sex + '\'' +
                ", employerType='" + employerType + '\'' +
                ", relationships=" + relationships +
                '}';
    }
}