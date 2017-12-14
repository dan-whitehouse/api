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
        "races",
        "hispanicLatinoEthnicity",
        "sex",
        "birthDate",
        "countryOfBirth",
        "usCitizenshipStatus"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Demographics {

    @JsonProperty("races")
    @XmlElement(name = "races")
    private Races races;
    @JsonProperty("hispanicLatinoEthnicity")
    @XmlElement(name = "hispanicLatinoEthnicity")
    private String hispanicLatinoEthnicity;
    @JsonProperty("sex")
    @XmlElement(name = "sex")
    private String sex;
    @JsonProperty("birthDate")
    @XmlElement(name = "birthDate")
    private String birthDate;
    @JsonProperty("countryOfBirth")
    @XmlElement(name = "countryOfBirth")
    private String countryOfBirth;
    @JsonProperty("usCitizenshipStatus")
    @XmlElement(name = "usCitizenshipStatus")
    private String usCitizenshipStatus;

    public Demographics() {
    }

    public Demographics(Races races, String hispanicLatinoEthnicity, String sex, String birthDate, String countryOfBirth, String usCitizenshipStatus) {
        super();
        this.races = races;
        this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
        this.sex = sex;
        this.birthDate = birthDate;
        this.countryOfBirth = countryOfBirth;
        this.usCitizenshipStatus = usCitizenshipStatus;
    }

    @JsonProperty("races")
    public Races getRaces() {
        return races;
    }

    @JsonProperty("races")
    public void setRaces(Races races) {
        this.races = races;
    }

    @JsonProperty("hispanicLatinoEthnicity")
    public String getHispanicLatinoEthnicity() {
        return hispanicLatinoEthnicity;
    }

    @JsonProperty("hispanicLatinoEthnicity")
    public void setHispanicLatinoEthnicity(String hispanicLatinoEthnicity) {
        this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
    }

    @JsonProperty("sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonProperty("birthDate")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birthDate")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("countryOfBirth")
    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    @JsonProperty("countryOfBirth")
    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    @JsonProperty("usCitizenshipStatus")
    public String getUsCitizenshipStatus() {
        return usCitizenshipStatus;
    }

    @JsonProperty("usCitizenshipStatus")
    public void setUsCitizenshipStatus(String usCitizenshipStatus) {
        this.usCitizenshipStatus = usCitizenshipStatus;
    }

    @Override
    public String toString() {
        return "Demographics{" +
                "races=" + races +
                ", hispanicLatinoEthnicity='" + hispanicLatinoEthnicity + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", countryOfBirth='" + countryOfBirth + '\'' +
                ", usCitizenshipStatus='" + usCitizenshipStatus + '\'' +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(races, hispanicLatinoEthnicity, sex, birthDate, countryOfBirth, usCitizenshipStatus).allMatch(Objects::isNull);
    }
}