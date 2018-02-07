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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xSchool"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XSchools {

    @JsonProperty("xSchool")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "xSchool")
    private List<XSchool> xSchool;

    public XSchools() {
        xSchool = new ArrayList<>();
    }

    public XSchools(List<XSchool> xSchool) {
        super();
        this.xSchool = xSchool;
    }

    @JsonProperty("xSchool")
    public List<XSchool> getXSchool() {
        return xSchool;
    }

    @JsonProperty("xSchool")
    public void setXSchool(List<XSchool> xSchool) {
        this.xSchool = xSchool;
    }

    @Override
    public String toString() {
        return "XSchools{" + "xSchool=" + xSchool + '}';
    }
}