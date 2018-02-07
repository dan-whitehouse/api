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
@JsonPropertyOrder({"emailType", "emailAddress"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Email {

    @JsonProperty("emailType")
    @XmlElement(name = "emailType")
    private String emailType;
    @JsonProperty("emailAddress")
    @XmlElement(name = "emailAddress")
    private String emailAddress;

    public Email() {
    }

    public Email(String emailType, String emailAddress) {
        super();
        this.emailType = emailType;
        this.emailAddress = emailAddress;
    }

    @JsonProperty("emailType")
    public String getEmailType() {
        return emailType;
    }

    @JsonProperty("emailType")
    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    @JsonProperty("emailAddress")
    public String getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty("emailAddress")
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Email{" + "emailType='" + emailType + '\'' + ", emailAddress='" + emailAddress + '\'' + '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return Stream.of(emailType, emailAddress).allMatch(Objects::isNull);
    }
}