/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "applicableEducationLevel"
})
public class ApplicableEducationLevels {

    @JsonProperty("applicableEducationLevel")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> applicableEducationLevel;


    public ApplicableEducationLevels() {
        applicableEducationLevel = new ArrayList<>();
    }

    public ApplicableEducationLevels(List<String> applicableEducationLevel) {
        super();
        this.applicableEducationLevel = applicableEducationLevel;
    }

    @JsonProperty("applicableEducationLevel")
    public List<String> getApplicableEducationLevel() {
        return applicableEducationLevel;
    }

    @JsonProperty("applicableEducationLevel")
    public void setApplicableEducationLevel(List<String> applicableEducationLevel) {
        this.applicableEducationLevel = applicableEducationLevel;
    }

    @Override
    public String toString() {
        return "ApplicableEducationLevels{" +
                "applicableEducationLevel=" + applicableEducationLevel +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return applicableEducationLevel.isEmpty();
    }
}