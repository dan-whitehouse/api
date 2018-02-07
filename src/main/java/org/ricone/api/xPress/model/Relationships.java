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
@JsonPropertyOrder({"relationship"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Relationships {

    @JsonProperty("relationship")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "relationship")
    private List<Relationship> relationship;

    public Relationships() {
        relationship = new ArrayList<>();
    }

    public Relationships(List<Relationship> relationship) {
        super();
        this.relationship = relationship;
    }

    @JsonProperty("relationship")
    public List<Relationship> getRelationship() {
        return relationship;
    }

    @JsonProperty("relationship")
    public void setRelationship(List<Relationship> relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "Relationships{" + "relationship=" + relationship + '}';
    }
}