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
@JsonPropertyOrder({"name"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OtherNames {

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "name")
    private List<Name> name;

    public OtherNames() {
        name = new ArrayList<>();
    }

    public OtherNames(List<Name> name) {
        super();
        this.name = name;
    }

    @JsonProperty("name")
    public List<Name> getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(List<Name> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OtherNames{" + "name=" + name + '}';
    }
}