/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xPress.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "staffPersonReference",
        "teacherOfRecord",
        "percentResponsible"
})
public class PrimaryStaff {

    @JsonProperty("staffPersonReference")
    private StaffPersonReference staffPersonReference;
    @JsonProperty("teacherOfRecord")
    private String teacherOfRecord;
    @JsonProperty("percentResponsible")
    private String percentResponsible;

    public PrimaryStaff() {
    }

    public PrimaryStaff(StaffPersonReference staffPersonReference, String teacherOfRecord, String percentResponsible) {
        super();
        this.staffPersonReference = staffPersonReference;
        this.teacherOfRecord = teacherOfRecord;
        this.percentResponsible = percentResponsible;
    }

    @JsonProperty("staffPersonReference")
    public StaffPersonReference getStaffPersonReference() {
        return staffPersonReference;
    }

    @JsonProperty("staffPersonReference")
    public void setStaffPersonReference(StaffPersonReference staffPersonReference) {
        this.staffPersonReference = staffPersonReference;
    }

    @JsonProperty("teacherOfRecord")
    public String getTeacherOfRecord() {
        return teacherOfRecord;
    }

    @JsonProperty("teacherOfRecord")
    public void setTeacherOfRecord(String teacherOfRecord) {
        this.teacherOfRecord = teacherOfRecord;
    }

    @JsonProperty("percentResponsible")
    public String getPercentResponsible() {
        return percentResponsible;
    }

    @JsonProperty("percentResponsible")
    public void setPercentResponsible(String percentResponsible) {
        this.percentResponsible = percentResponsible;
    }

    @Override
    public String toString() {
        return "PrimaryStaff{" +
                "staffPersonReference=" + staffPersonReference +
                ", teacherOfRecord='" + teacherOfRecord + '\'' +
                ", percentResponsible='" + percentResponsible + '\'' +
                '}';
    }

    @JsonIgnore
    public boolean isEmptyObject()
    {
        return Stream.of(staffPersonReference, teacherOfRecord, percentResponsible).allMatch(Objects::isNull);
    }
}