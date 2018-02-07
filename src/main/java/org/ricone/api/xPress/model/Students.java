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
@JsonPropertyOrder({"studentReference"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Students {

    @JsonProperty("studentReference")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "studentReference")
    private List<StudentReference> studentReference;

    public Students() {
        studentReference = new ArrayList<>();
    }

    public Students(List<StudentReference> studentReference) {
        super();
        this.studentReference = studentReference;
    }

    @JsonProperty("studentReference")
    public List<StudentReference> getStudentReference() {
        return studentReference;
    }

    @JsonProperty("studentReference")
    public void setStudentReference(List<StudentReference> studentReference) {
        this.studentReference = studentReference;
    }

    @Override
    public String toString() {
        return "Students{" + "studentReference=" + studentReference + '}';
    }
}