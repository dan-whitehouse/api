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
        "refId",
        "localId",
        "givenName",
        "familyName"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StaffPersonReference {

    @JsonProperty("refId")
    @XmlElement(name = "refId")
    private String refId;
    @JsonProperty("localId")
    @XmlElement(name = "localId")
    private String localId;
    @JsonProperty("givenName")
    @XmlElement(name = "givenName")
    private String givenName;
    @JsonProperty("familyName")
    @XmlElement(name = "familyName")
    private String familyName;

    public StaffPersonReference() {
    }

    public StaffPersonReference(String refId, String localId, String givenName, String familyName) {
        super();
        this.refId = refId;
        this.localId = localId;
        this.givenName = givenName;
        this.familyName = familyName;
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

    @JsonProperty("givenName")
    public String getGivenName() {
        return givenName;
    }

    @JsonProperty("givenName")
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @JsonProperty("familyName")
    public String getFamilyName() {
        return familyName;
    }

    @JsonProperty("familyName")
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public String toString() {
        return "StaffPersonReference{" +
                "refId='" + refId + '\'' +
                ", localId='" + localId + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(refId, localId, givenName, familyName).allMatch(Objects::isNull);
    }
}