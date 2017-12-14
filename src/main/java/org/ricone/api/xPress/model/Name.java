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
        "type",
        "prefix",
        "familyName",
        "givenName",
        "middleName",
        "suffix"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Name {

    @JsonProperty("type")
    @XmlElement(name = "type")
    private String type;
    @JsonProperty("prefix")
    @XmlElement(name = "prefix")
    private String prefix;
    @JsonProperty("familyName")
    @XmlElement(name = "familyName")
    private String familyName;
    @JsonProperty("givenName")
    @XmlElement(name = "givenName")
    private String givenName;
    @JsonProperty("middleName")
    @XmlElement(name = "middleName")
    private String middleName;
    @JsonProperty("suffix")
    @XmlElement(name = "suffix")
    private String suffix;

    public Name() {
    }

    public Name(String type, String prefix, String familyName, String givenName, String middleName, String suffix) {
        super();
        this.type = type;
        this.prefix = prefix;
        this.familyName = familyName;
        this.givenName = givenName;
        this.middleName = middleName;
        this.suffix = suffix;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("prefix")
    public String getPrefix() {
        return prefix;
    }

    @JsonProperty("prefix")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @JsonProperty("familyName")
    public String getFamilyName() {
        return familyName;
    }

    @JsonProperty("familyName")
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @JsonProperty("givenName")
    public String getGivenName() {
        return givenName;
    }

    @JsonProperty("givenName")
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @JsonProperty("middleName")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middleName")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("suffix")
    public String getSuffix() {
        return suffix;
    }

    @JsonProperty("suffix")
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return "Name{" +
                "type='" + type + '\'' +
                ", prefix='" + prefix + '\'' +
                ", familyName='" + familyName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(type, prefix, familyName, givenName, middleName, suffix).allMatch(Objects::isNull);
    }
}