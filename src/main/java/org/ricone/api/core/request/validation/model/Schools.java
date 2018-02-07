package org.ricone.api.core.request.validation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"school"})
public class Schools {

    @JsonProperty("school")
    private List<School> school = new ArrayList<School>();

    public Schools() {
    }

    public Schools(List<School> school) {
        super();
        this.school = school;
    }

    @JsonProperty("school")
    public List<School> getSchool() {
        return school;
    }

    @JsonProperty("school")
    public void setSchool(List<School> school) {
        this.school = school;
    }
}