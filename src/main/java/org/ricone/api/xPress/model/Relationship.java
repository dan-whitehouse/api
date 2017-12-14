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
        "studentRefId",
        "relationshipCode",
        "restrictions",
        "livesWith",
        "primaryContactIndicator",
        "emergencyContactIndicator",
        "financialResponsibilityIndicator",
        "custodialIndicator",
        "communicationsIndicator",
        "contactSequence"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Relationship {

    @JsonProperty("studentRefId")
    @XmlElement(name = "studentRefId")
    private String studentRefId;
    @JsonProperty("relationshipCode")
    @XmlElement(name = "relationshipCode")
    private String relationshipCode;
    @JsonProperty("restrictions")
    @XmlElement(name = "restrictions")
    private String restrictions;
    @JsonProperty("livesWith")
    @XmlElement(name = "livesWith")
    private String livesWith;
    @JsonProperty("primaryContactIndicator")
    @XmlElement(name = "primaryContactIndicator")
    private String primaryContactIndicator;
    @JsonProperty("emergencyContactIndicator")
    @XmlElement(name = "emergencyContactIndicator")
    private String emergencyContactIndicator;
    @JsonProperty("financialResponsibilityIndicator")
    @XmlElement(name = "financialResponsibilityIndicator")
    private String financialResponsibilityIndicator;
    @JsonProperty("custodialIndicator")
    @XmlElement(name = "custodialIndicator")
    private String custodialIndicator;
    @JsonProperty("communicationsIndicator")
    @XmlElement(name = "communicationsIndicator")
    private String communicationsIndicator;
    @JsonProperty("contactSequence")
    @XmlElement(name = "contactSequence")
    private String contactSequence;

    public Relationship() {
    }

    public Relationship(String studentRefId, String relationshipCode, String restrictions, String livesWith, String primaryContactIndicator, String emergencyContactIndicator, String financialResponsibilityIndicator, String custodialIndicator, String communicationsIndicator, String contactSequence) {
        super();
        this.studentRefId = studentRefId;
        this.relationshipCode = relationshipCode;
        this.restrictions = restrictions;
        this.livesWith = livesWith;
        this.primaryContactIndicator = primaryContactIndicator;
        this.emergencyContactIndicator = emergencyContactIndicator;
        this.financialResponsibilityIndicator = financialResponsibilityIndicator;
        this.custodialIndicator = custodialIndicator;
        this.communicationsIndicator = communicationsIndicator;
        this.contactSequence = contactSequence;
    }

    @JsonProperty("studentRefId")
    public String getStudentRefId() {
        return studentRefId;
    }

    @JsonProperty("studentRefId")
    public void setStudentRefId(String studentRefId) {
        this.studentRefId = studentRefId;
    }

    @JsonProperty("relationshipCode")
    public String getRelationshipCode() {
        return relationshipCode;
    }

    @JsonProperty("relationshipCode")
    public void setRelationshipCode(String relationshipCode) {
        this.relationshipCode = relationshipCode;
    }

    @JsonProperty("restrictions")
    public String getRestrictions() {
        return restrictions;
    }

    @JsonProperty("restrictions")
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    @JsonProperty("livesWith")
    public String getLivesWith() {
        return livesWith;
    }

    @JsonProperty("livesWith")
    public void setLivesWith(String livesWith) {
        this.livesWith = livesWith;
    }

    @JsonProperty("primaryContactIndicator")
    public String getPrimaryContactIndicator() {
        return primaryContactIndicator;
    }

    @JsonProperty("primaryContactIndicator")
    public void setPrimaryContactIndicator(String primaryContactIndicator) {
        this.primaryContactIndicator = primaryContactIndicator;
    }

    @JsonProperty("emergencyContactIndicator")
    public String getEmergencyContactIndicator() {
        return emergencyContactIndicator;
    }

    @JsonProperty("emergencyContactIndicator")
    public void setEmergencyContactIndicator(String emergencyContactIndicator) {
        this.emergencyContactIndicator = emergencyContactIndicator;
    }

    @JsonProperty("financialResponsibilityIndicator")
    public String getFinancialResponsibilityIndicator() {
        return financialResponsibilityIndicator;
    }

    @JsonProperty("financialResponsibilityIndicator")
    public void setFinancialResponsibilityIndicator(String financialResponsibilityIndicator) {
        this.financialResponsibilityIndicator = financialResponsibilityIndicator;
    }

    @JsonProperty("custodialIndicator")
    public String getCustodialIndicator() {
        return custodialIndicator;
    }

    @JsonProperty("custodialIndicator")
    public void setCustodialIndicator(String custodialIndicator) {
        this.custodialIndicator = custodialIndicator;
    }

    @JsonProperty("communicationsIndicator")
    public String getCommunicationsIndicator() {
        return communicationsIndicator;
    }

    @JsonProperty("communicationsIndicator")
    public void setCommunicationsIndicator(String communicationsIndicator) {
        this.communicationsIndicator = communicationsIndicator;
    }

    @JsonProperty("contactSequence")
    public String getContactSequence() {
        return contactSequence;
    }

    @JsonProperty("contactSequence")
    public void setContactSequence(String contactSequence) {
        this.contactSequence = contactSequence;
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "studentRefId='" + studentRefId + '\'' +
                ", relationshipCode='" + relationshipCode + '\'' +
                ", restrictions='" + restrictions + '\'' +
                ", livesWith='" + livesWith + '\'' +
                ", primaryContactIndicator='" + primaryContactIndicator + '\'' +
                ", emergencyContactIndicator='" + emergencyContactIndicator + '\'' +
                ", financialResponsibilityIndicator='" + financialResponsibilityIndicator + '\'' +
                ", custodialIndicator='" + custodialIndicator + '\'' +
                ", communicationsIndicator='" + communicationsIndicator + '\'' +
                ", contactSequence='" + contactSequence + '\'' +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(studentRefId, relationshipCode, restrictions, livesWith, primaryContactIndicator, emergencyContactIndicator, financialResponsibilityIndicator, custodialIndicator, communicationsIndicator, contactSequence).allMatch(Objects::isNull);
    }
}