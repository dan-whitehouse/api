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
        "stateProvinceId",
        "otherIds",
        "address",
        "phoneNumber",
        "otherPhoneNumbers",
        "email",
        "otherEmails",
        "demographics",
        "enrollment",
        "otherEnrollments",
        "academicSummary",
        "studentContacts",
        "languages"
})
public class XStudent extends XWrapper {

    @JsonProperty("@refId")
    private String refId;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("otherNames")
    private OtherNames otherNames;
    @JsonProperty("localId")
    private String localId;
    @JsonProperty("stateProvinceId")
    private String stateProvinceId;
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
    @JsonProperty("demographics")
    private Demographics demographics;
    @JsonProperty("enrollment")
    private Enrollment enrollment;
    @JsonProperty("otherEnrollments")
    private OtherEnrollments otherEnrollments;
    @JsonProperty("academicSummary")
    private AcademicSummary academicSummary;
    @JsonProperty("studentContacts")
    private StudentContacts studentContacts;
    @JsonProperty("languages")
    private Languages languages;

    public XStudent() {
    }

    public XStudent(String refId) {
        this.refId = refId;
    }

    public XStudent(String refId, Name name, OtherNames otherNames, String localId, String stateProvinceId, OtherIds otherIds, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Email email, OtherEmails otherEmails, Demographics demographics, Enrollment enrollment, OtherEnrollments otherEnrollments, AcademicSummary academicSummary, StudentContacts studentContacts, Languages languages) {
        super();
        this.refId = refId;
        this.name = name;
        this.otherNames = otherNames;
        this.localId = localId;
        this.stateProvinceId = stateProvinceId;
        this.otherIds = otherIds;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.otherPhoneNumbers = otherPhoneNumbers;
        this.email = email;
        this.otherEmails = otherEmails;
        this.demographics = demographics;
        this.enrollment = enrollment;
        this.otherEnrollments = otherEnrollments;
        this.academicSummary = academicSummary;
        this.studentContacts = studentContacts;
        this.languages = languages;
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

    @JsonProperty("demographics")
    public Demographics getDemographics() {
        return demographics;
    }

    @JsonProperty("demographics")
    public void setDemographics(Demographics demographics) {
        this.demographics = demographics;
    }

    @JsonProperty("enrollment")
    public Enrollment getEnrollment() {
        return enrollment;
    }

    @JsonProperty("enrollment")
    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    @JsonProperty("otherEnrollments")
    public OtherEnrollments getOtherEnrollments() {
        return otherEnrollments;
    }

    @JsonProperty("otherEnrollments")
    public void setOtherEnrollments(OtherEnrollments otherEnrollments) {
        this.otherEnrollments = otherEnrollments;
    }

    @JsonProperty("academicSummary")
    public AcademicSummary getAcademicSummary() {
        return academicSummary;
    }

    @JsonProperty("academicSummary")
    public void setAcademicSummary(AcademicSummary academicSummary) {
        this.academicSummary = academicSummary;
    }

    @JsonProperty("studentContacts")
    public StudentContacts getStudentContacts() {
        return studentContacts;
    }

    @JsonProperty("studentContacts")
    public void setStudentContacts(StudentContacts studentContacts) {
        this.studentContacts = studentContacts;
    }

    @JsonProperty("languages")
    public Languages getLanguages() {
        return languages;
    }

    @JsonProperty("languages")
    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "XStudent{" +
                "refId='" + refId + '\'' +
                ", name=" + name +
                ", otherNames=" + otherNames +
                ", localId='" + localId + '\'' +
                ", stateProvinceId='" + stateProvinceId + '\'' +
                ", otherIds=" + otherIds +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                ", otherPhoneNumbers=" + otherPhoneNumbers +
                ", email=" + email +
                ", otherEmails=" + otherEmails +
                ", demographics=" + demographics +
                ", enrollment=" + enrollment +
                ", otherEnrollments=" + otherEnrollments +
                ", academicSummary=" + academicSummary +
                ", studentContacts=" + studentContacts +
                ", languages=" + languages +
                '}';
    }
}