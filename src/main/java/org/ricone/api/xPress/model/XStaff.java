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
        "localId",
        "stateProvinceId",
        "otherIds",
        "sex",
        "email",
        "primaryAssignment",
        "otherAssignments"
})
public class XStaff {

    @JsonProperty("@refId")
    private String refId;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("localId")
    private String localId;
    @JsonProperty("stateProvinceId")
    private String stateProvinceId;
    @JsonProperty("otherIds")
    private OtherIds otherIds;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("email")
    private Email email;
    @JsonProperty("primaryAssignment")
    private PrimaryAssignment primaryAssignment;
    @JsonProperty("otherAssignments")
    private OtherAssignments otherAssignments;

    public XStaff() {
    }

    public XStaff(String refId) {
        this.refId = refId;
    }
    
    public XStaff(String refId, Name name, String localId, String stateProvinceId, OtherIds otherIds, String sex, Email email, PrimaryAssignment primaryAssignment, OtherAssignments otherAssignments) {
        super();
        this.refId = refId;
        this.name = name;
        this.localId = localId;
        this.stateProvinceId = stateProvinceId;
        this.otherIds = otherIds;
        this.sex = sex;
        this.email = email;
        this.primaryAssignment = primaryAssignment;
        this.otherAssignments = otherAssignments;
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

    @JsonProperty("sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonProperty("email")
    public Email getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(Email email) {
        this.email = email;
    }

    @JsonProperty("primaryAssignment")
    public PrimaryAssignment getPrimaryAssignment() {
        return primaryAssignment;
    }

    @JsonProperty("primaryAssignment")
    public void setPrimaryAssignment(PrimaryAssignment primaryAssignment) {
        this.primaryAssignment = primaryAssignment;
    }

    @JsonProperty("otherAssignments")
    public OtherAssignments getOtherAssignments() {
        return otherAssignments;
    }

    @JsonProperty("otherAssignments")
    public void setOtherAssignments(OtherAssignments otherAssignments) {
        this.otherAssignments = otherAssignments;
    }

    @Override
    public String toString() {
        return "XStaff{" +
                "refId='" + refId + '\'' +
                ", name=" + name +
                ", localId='" + localId + '\'' +
                ", stateProvinceId='" + stateProvinceId + '\'' +
                ", otherIds=" + otherIds +
                ", sex='" + sex + '\'' +
                ", email=" + email +
                ", primaryAssignment=" + primaryAssignment +
                ", otherAssignments=" + otherAssignments +
                '}';
    }
}