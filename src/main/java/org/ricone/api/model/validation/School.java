package org.ricone.api.model.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "refId",
    "name",
    "studentEnrollments",
    "staffAssignments",
    "courses",
    "courseSections"
})
public class School {

    @JsonProperty("refId")
    private String refId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("studentEnrollments")
    private Integer studentEnrollments;
    @JsonProperty("staffAssignments")
    private Integer staffAssignments;
    @JsonProperty("courses")
    private Integer courses;
    @JsonProperty("courseSections")
    private Integer courseSections;

    public School() {
    }

    public School(String refId, String name, Integer studentEnrollments, Integer staffAssignments, Integer courses, Integer courseSections) {
        super();
        this.refId = refId;
        this.name = name;
        this.studentEnrollments = studentEnrollments;
        this.staffAssignments = staffAssignments;
        this.courses = courses;
        this.courseSections = courseSections;
    }

    @JsonProperty("refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("studentEnrollments")
    public Integer getStudentEnrollments() {
        return studentEnrollments;
    }

    @JsonProperty("studentEnrollments")
    public void setStudentEnrollments(Integer studentEnrollments) {
        this.studentEnrollments = studentEnrollments;
    }

    @JsonProperty("staffAssignments")
    public Integer getStaffAssignments() {
        return staffAssignments;
    }

    @JsonProperty("staffAssignments")
    public void setStaffAssignments(Integer staffAssignments) {
        this.staffAssignments = staffAssignments;
    }

    @JsonProperty("courses")
    public Integer getCourses() {
        return courses;
    }

    @JsonProperty("courses")
    public void setCourses(Integer courses) {
        this.courses = courses;
    }

    @JsonProperty("courseSections")
    public Integer getCourseSections() {
        return courseSections;
    }

    @JsonProperty("courseSections")
    public void setCourseSections(Integer courseSections) {
        this.courseSections = courseSections;
    }
}