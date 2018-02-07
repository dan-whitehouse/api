/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "otherCode"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExitType {

    @JsonProperty("code")
    @XmlElement(name = "code")
    private String code;

    @JsonProperty("otherCode")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "otherCode")
    private List<OtherCode> otherCode;

    /**
     * No args constructor for use in serialization
     */
    public ExitType() {
        otherCode = new ArrayList<>();
    }

    public ExitType(String code, List<OtherCode> otherCode) {
        super();
        this.code = code;
        this.otherCode = otherCode;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("otherCode")
    public List<OtherCode> getOtherCode() {
        return otherCode;
    }

    @JsonProperty("otherCode")
    public void setOtherCode(List<OtherCode> otherCode) {
        this.otherCode = otherCode;
    }

    @Override
    public String toString() {
        return "ExitType{" + "code='" + code + '\'' + ", otherCode=" + otherCode + '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return Stream.of(code, otherCode).allMatch(Objects::isNull) || (StringUtils.isBlank(code) && otherCode.isEmpty());
    }
}