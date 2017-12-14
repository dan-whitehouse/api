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
        "cumulativeWeightedGpa",
        "termWeightedGpa",
        "classRank"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AcademicSummary {

    @JsonProperty("cumulativeWeightedGpa")
    @XmlElement(name = "__________")
    private String cumulativeWeightedGpa;
    @JsonProperty("termWeightedGpa")
    @XmlElement(name = "termWeightedGpa")
    private String termWeightedGpa;
    @JsonProperty("classRank")
    @XmlElement(name = "classRank")
    private String classRank;

    public AcademicSummary() {
    }

    public AcademicSummary(String cumulativeWeightedGpa, String termWeightedGpa, String classRank) {
        super();
        this.cumulativeWeightedGpa = cumulativeWeightedGpa;
        this.termWeightedGpa = termWeightedGpa;
        this.classRank = classRank;
    }

    @JsonProperty("cumulativeWeightedGpa")
    public String getCumulativeWeightedGpa() {
        return cumulativeWeightedGpa;
    }

    @JsonProperty("cumulativeWeightedGpa")
    public void setCumulativeWeightedGpa(String cumulativeWeightedGpa) {
        this.cumulativeWeightedGpa = cumulativeWeightedGpa;
    }

    @JsonProperty("termWeightedGpa")
    public String getTermWeightedGpa() {
        return termWeightedGpa;
    }

    @JsonProperty("termWeightedGpa")
    public void setTermWeightedGpa(String termWeightedGpa) {
        this.termWeightedGpa = termWeightedGpa;
    }

    @JsonProperty("classRank")
    public String getClassRank() {
        return classRank;
    }

    @JsonProperty("classRank")
    public void setClassRank(String classRank) {
        this.classRank = classRank;
    }

    @Override
    public String toString() {
        return "AcademicSummary{" +
                "cumulativeWeightedGpa='" + cumulativeWeightedGpa + '\'' +
                ", termWeightedGpa='" + termWeightedGpa + '\'' +
                ", classRank='" + classRank + '\'' +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(cumulativeWeightedGpa, termWeightedGpa, classRank).allMatch(Objects::isNull);
    }
}