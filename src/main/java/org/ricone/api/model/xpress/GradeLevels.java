/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.model.xpress;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gradeLevel"
})
public class GradeLevels {

    @JsonProperty("gradeLevel")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> gradeLevel;

    public GradeLevels() {
        gradeLevel = new ArrayList<>();
    }

    public GradeLevels(List<String> gradeLevel) {
        super();
        this.gradeLevel = gradeLevel;
    }

    @JsonProperty("gradeLevel")
    public List<String> getGradeLevel() {
        return gradeLevel;
    }

    @JsonProperty("gradeLevel")
    public void setGradeLevel(List<String> gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    @Override
    public String toString() {
        return "GradeLevels{" +
                "gradeLevel=" + gradeLevel +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject() {
        return gradeLevel.isEmpty();
    }
}