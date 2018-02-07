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
@JsonPropertyOrder({"xStudent"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XStudents {

    @JsonProperty("xStudent")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "xStudent")
    private List<XStudent> xStudent;

    public XStudents() {
        xStudent = new ArrayList<>();
    }

    public XStudents(List<XStudent> xStudent) {
        super();
        this.xStudent = xStudent;
    }

    @JsonProperty("xStudent")
    public List<XStudent> getXStudent() {
        return xStudent;
    }

    @JsonProperty("xStudent")
    public void setXStudent(List<XStudent> xStudent) {
        this.xStudent = xStudent;
    }

    @Override
    public String toString() {
        return "XStudents{" + "xStudent=" + xStudent + '}';
    }
}