/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.model.xpress;

import com.fasterxml.jackson.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "leaRefId",
        "schoolRefId",
        "jobFunction"
})
public class PrimaryAssignment {

    @JsonProperty("leaRefId")
    private String leaRefId;
    @JsonProperty("schoolRefId")
    private String schoolRefId;
    @JsonProperty("jobFunction")
    private String jobFunction;

    public PrimaryAssignment() {
    }

    public PrimaryAssignment(String leaRefId, String schoolRefId, String jobFunction) {
        super();
        this.leaRefId = leaRefId;
        this.schoolRefId = schoolRefId;
        this.jobFunction = jobFunction;
    }

    @JsonProperty("leaRefId")
    public String getLeaRefId() {
        return leaRefId;
    }

    @JsonProperty("leaRefId")
    public void setLeaRefId(String leaRefId) {
        this.leaRefId = leaRefId;
    }

    @JsonProperty("schoolRefId")
    public String getSchoolRefId() {
        return schoolRefId;
    }

    @JsonProperty("schoolRefId")
    public void setSchoolRefId(String schoolRefId) {
        this.schoolRefId = schoolRefId;
    }

    @JsonProperty("jobFunction")
    public String getJobFunction() {
        return jobFunction;
    }

    @JsonProperty("jobFunction")
    public void setJobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    @Override
    public String toString() {
        return "PrimaryAssignment{" +
                "leaRefId='" + leaRefId + '\'' +
                ", schoolRefId='" + schoolRefId + '\'' +
                ", jobFunction='" + jobFunction + '\'' +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(leaRefId, schoolRefId, jobFunction).allMatch(Objects::isNull);
    }
}