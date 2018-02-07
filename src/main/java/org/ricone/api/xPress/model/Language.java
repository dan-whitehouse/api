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
@JsonPropertyOrder({"type", "code"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Language {

    @JsonProperty("type")
    @XmlElement(name = "type")
    private String type;
    @JsonProperty("code")
    @XmlElement(name = "code")
    private String code;

    public Language() {
    }

    public Language(String type, String code) {
        super();
        this.type = type;
        this.code = code;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Language{" + "type='" + type + '\'' + ", code='" + code + '\'' + '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return Stream.of(type, code).allMatch(Objects::isNull);
    }
}