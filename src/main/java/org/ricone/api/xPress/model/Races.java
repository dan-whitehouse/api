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
@JsonPropertyOrder({
        "race"
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Races {

    @JsonProperty("race")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @XmlElement(name = "race")
    private List<Race> race;

    public Races() {
        race = new ArrayList<>();
    }

    public Races(List<Race> race) {
        super();
        this.race = race;
    }

    @JsonProperty("race")
    public List<Race> getRace() {
        return race;
    }

    @JsonProperty("race")
    public void setRace(List<Race> race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Races{" +
                "race=" + race +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return race.isEmpty();
    }
}