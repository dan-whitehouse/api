/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.model.xpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "@refId",
        "courseRefId",
        "courseTitle",
        "sectionRefId",
        "subject",
        "schoolRefId",
        "schoolSectionId",
        "schoolYear",
        "sessionCode",
        "schoolCalendarRefId",
        "meetingTimes",
        "students",
        "primaryStaff",
        "otherStaffs"
})
public class XRoster {

    @JsonProperty("@refId")
    private String refId;
    @JsonProperty("courseRefId")
    private String courseRefId;
    @JsonProperty("courseTitle")
    private String courseTitle;
    @JsonProperty("sectionRefId")
    private String sectionRefId;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("schoolRefId")
    private String schoolRefId;
    @JsonProperty("schoolSectionId")
    private String schoolSectionId;
    @JsonProperty("schoolYear")
    private String schoolYear;
    @JsonProperty("sessionCode")
    private String sessionCode;
    @JsonProperty("schoolCalendarRefId")
    private String schoolCalendarRefId;
    @JsonProperty("meetingTimes")
    private MeetingTimes meetingTimes;
    @JsonProperty("students")
    private Students students;
    @JsonProperty("primaryStaff")
    private PrimaryStaff primaryStaff;
    @JsonProperty("otherStaffs")
    private OtherStaffs otherStaffs;

    public XRoster() {
    }

    public XRoster(String refId) {
        this.refId = refId;
    }

    public XRoster(String refId, String courseRefId, String courseTitle, String sectionRefId, String subject, String schoolRefId, String schoolSectionId, String schoolYear, String sessionCode, String schoolCalendarRefId, MeetingTimes meetingTimes, Students students, PrimaryStaff primaryStaff, OtherStaffs otherStaffs) {
        super();
        this.refId = refId;
        this.courseRefId = courseRefId;
        this.courseTitle = courseTitle;
        this.sectionRefId = sectionRefId;
        this.subject = subject;
        this.schoolRefId = schoolRefId;
        this.schoolSectionId = schoolSectionId;
        this.schoolYear = schoolYear;
        this.sessionCode = sessionCode;
        this.schoolCalendarRefId = schoolCalendarRefId;
        this.meetingTimes = meetingTimes;
        this.students = students;
        this.primaryStaff = primaryStaff;
        this.otherStaffs = otherStaffs;
    }

    @JsonProperty("@refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("@refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("courseRefId")
    public String getCourseRefId() {
        return courseRefId;
    }

    @JsonProperty("courseRefId")
    public void setCourseRefId(String courseRefId) {
        this.courseRefId = courseRefId;
    }

    @JsonProperty("courseTitle")
    public String getCourseTitle() {
        return courseTitle;
    }

    @JsonProperty("courseTitle")
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @JsonProperty("sectionRefId")
    public String getSectionRefId() {
        return sectionRefId;
    }

    @JsonProperty("sectionRefId")
    public void setSectionRefId(String sectionRefId) {
        this.sectionRefId = sectionRefId;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("schoolRefId")
    public String getSchoolRefId() {
        return schoolRefId;
    }

    @JsonProperty("schoolRefId")
    public void setSchoolRefId(String schoolRefId) {
        this.schoolRefId = schoolRefId;
    }

    @JsonProperty("schoolSectionId")
    public String getSchoolSectionId() {
        return schoolSectionId;
    }

    @JsonProperty("schoolSectionId")
    public void setSchoolSectionId(String schoolSectionId) {
        this.schoolSectionId = schoolSectionId;
    }

    @JsonProperty("schoolYear")
    public String getSchoolYear() {
        return schoolYear;
    }

    @JsonProperty("schoolYear")
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @JsonProperty("sessionCode")
    public String getSessionCode() {
        return sessionCode;
    }

    @JsonProperty("sessionCode")
    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    @JsonProperty("schoolCalendarRefId")
    public String getSchoolCalendarRefId() {
        return schoolCalendarRefId;
    }

    @JsonProperty("schoolCalendarRefId")
    public void setSchoolCalendarRefId(String schoolCalendarRefId) {
        this.schoolCalendarRefId = schoolCalendarRefId;
    }

    @JsonProperty("meetingTimes")
    public MeetingTimes getMeetingTimes() {
        return meetingTimes;
    }

    @JsonProperty("meetingTimes")
    public void setMeetingTimes(MeetingTimes meetingTimes) {
        this.meetingTimes = meetingTimes;
    }

    @JsonProperty("students")
    public Students getStudents() {
        return students;
    }

    @JsonProperty("students")
    public void setStudents(Students students) {
        this.students = students;
    }

    @JsonProperty("primaryStaff")
    public PrimaryStaff getPrimaryStaff() {
        return primaryStaff;
    }

    @JsonProperty("primaryStaff")
    public void setPrimaryStaff(PrimaryStaff primaryStaff) {
        this.primaryStaff = primaryStaff;
    }

    @JsonProperty("otherStaffs")
    public OtherStaffs getOtherStaffs() {
        return otherStaffs;
    }

    @JsonProperty("otherStaffs")
    public void setOtherStaffs(OtherStaffs otherStaffs) {
        this.otherStaffs = otherStaffs;
    }

    @Override
    public String toString() {
        return "XRoster{" +
                "refId='" + refId + '\'' +
                ", courseRefId='" + courseRefId + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", sectionRefId='" + sectionRefId + '\'' +
                ", subject='" + subject + '\'' +
                ", schoolRefId='" + schoolRefId + '\'' +
                ", schoolSectionId='" + schoolSectionId + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", sessionCode='" + sessionCode + '\'' +
                ", schoolCalendarRefId='" + schoolCalendarRefId + '\'' +
                ", meetingTimes=" + meetingTimes +
                ", students=" + students +
                ", primaryStaff=" + primaryStaff +
                ", otherStaffs=" + otherStaffs +
                '}';
    }
}