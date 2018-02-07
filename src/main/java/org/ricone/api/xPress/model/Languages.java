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
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"language"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Languages {

    @JsonProperty("language")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "language")
    private List<Language> language;

    public Languages() {
        language = new ArrayList<>();
    }

    public Languages(List<Language> language) {
        super();
        this.language = language;
    }

    @JsonProperty("language")
    public List<Language> getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Languages{" + "language=" + language + '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return language.isEmpty();
    }
}