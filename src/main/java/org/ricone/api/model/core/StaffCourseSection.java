package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "staffcoursesection")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StaffCourseSection implements java.io.Serializable
{
    private static final long serialVersionUID = -180003633066217606L;
    private String staffCourseSectionRefId;
    private CourseSection courseSection;
    private Staff staff;
    private Boolean teacherOfRecord;
    private BigDecimal contributionPercentage;
    private String classroomPositionType;

    public StaffCourseSection()
    {
    }

    public StaffCourseSection(String staffCourseSectionRefId, CourseSection courseSection, Staff staff)
    {
        this.staffCourseSectionRefId = staffCourseSectionRefId;
        this.courseSection = courseSection;
        this.staff = staff;
    }

    public StaffCourseSection(String staffCourseSectionRefId, CourseSection courseSection, Staff staff, Boolean teacherOfRecord, BigDecimal contributionPercentage, String classroomPositionType)
    {
        this.staffCourseSectionRefId = staffCourseSectionRefId;
        this.courseSection = courseSection;
        this.staff = staff;
        this.teacherOfRecord = teacherOfRecord;
        this.contributionPercentage = contributionPercentage;
        this.classroomPositionType = classroomPositionType;
    }

    @Id
    @Column(name = "StaffCourseSectionRefId", unique = true, nullable = false, length = 64)
    public String getStaffCourseSectionRefId()
    {
        return this.staffCourseSectionRefId;
    }

    public void setStaffCourseSectionRefId(String staffCourseSectionRefId)
    {
        this.staffCourseSectionRefId = staffCourseSectionRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseSectionRefId", nullable = false)
    public CourseSection getCourseSection()
    {
        return this.courseSection;
    }

    public void setCourseSection(CourseSection courseSection)
    {
        this.courseSection = courseSection;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StaffRefId", nullable = false)
    public Staff getStaff()
    {
        return this.staff;
    }

    public void setStaff(Staff staff)
    {
        this.staff = staff;
    }

    @Column(name = "TeacherOfRecord")
    public Boolean getTeacherOfRecord()
    {
        return this.teacherOfRecord;
    }

    public void setTeacherOfRecord(Boolean teacherOfRecord)
    {
        this.teacherOfRecord = teacherOfRecord;
    }

    @Column(name = "ContributionPercentage", precision = 5)
    public BigDecimal getContributionPercentage()
    {
        return this.contributionPercentage;
    }

    public void setContributionPercentage(BigDecimal contributionPercentage)
    {
        this.contributionPercentage = contributionPercentage;
    }

    @Column(name = "ClassroomPositionType", length = 50)
    public String getClassroomPositionType()
    {
        return this.classroomPositionType;
    }

    public void setClassroomPositionType(String classroomPositionType)
    {
        this.classroomPositionType = classroomPositionType;
    }

}