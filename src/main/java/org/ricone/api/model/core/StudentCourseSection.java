package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@Table(name = "studentcoursesection")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentCourseSection implements java.io.Serializable
{
    private static final long serialVersionUID = 8992517437619935826L;
    private String studentCourseSectionRefId;
    private CourseSection courseSection;
    private Student student;
    private String courseSectionEnrollmentStatusTypeCode;
    private String courseSectionEntryTypeCode;
    private String courseSectionExitTypeCode;
    private String exitOrWithdrawalStatusCode;
    private String gradeLevelWhenCourseTakenCode;

    public StudentCourseSection()
    {
    }

    public StudentCourseSection(String studentCourseSectionRefId, CourseSection courseSection, Student student)
    {
        this.studentCourseSectionRefId = studentCourseSectionRefId;
        this.courseSection = courseSection;
        this.student = student;
    }

    public StudentCourseSection(String studentCourseSectionRefId, CourseSection courseSection, Student student, String courseSectionEnrollmentStatusTypeCode, String courseSectionEntryTypeCode, String courseSectionExitTypeCode, String exitOrWithdrawalStatusCode, String gradeLevelWhenCourseTakenCode)
    {
        this.studentCourseSectionRefId = studentCourseSectionRefId;
        this.courseSection = courseSection;
        this.student = student;
        this.courseSectionEnrollmentStatusTypeCode = courseSectionEnrollmentStatusTypeCode;
        this.courseSectionEntryTypeCode = courseSectionEntryTypeCode;
        this.courseSectionExitTypeCode = courseSectionExitTypeCode;
        this.exitOrWithdrawalStatusCode = exitOrWithdrawalStatusCode;
        this.gradeLevelWhenCourseTakenCode = gradeLevelWhenCourseTakenCode;
    }

    @Id
    @Column(name = "StudentCourseSectionRefId", unique = true, nullable = false, length = 64)
    public String getStudentCourseSectionRefId()
    {
        return this.studentCourseSectionRefId;
    }

    public void setStudentCourseSectionRefId(String studentCourseSectionRefId)
    {
        this.studentCourseSectionRefId = studentCourseSectionRefId;
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
    @JoinColumn(name = "StudentRefId", nullable = false)
    public Student getStudent()
    {
        return this.student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    @Column(name = "CourseSectionEnrollmentStatusTypeCode", length = 50)
    public String getCourseSectionEnrollmentStatusTypeCode()
    {
        return this.courseSectionEnrollmentStatusTypeCode;
    }

    public void setCourseSectionEnrollmentStatusTypeCode(String courseSectionEnrollmentStatusTypeCode)
    {
        this.courseSectionEnrollmentStatusTypeCode = courseSectionEnrollmentStatusTypeCode;
    }

    @Column(name = "CourseSectionEntryTypeCode", length = 50)
    public String getCourseSectionEntryTypeCode()
    {
        return this.courseSectionEntryTypeCode;
    }

    public void setCourseSectionEntryTypeCode(String courseSectionEntryTypeCode)
    {
        this.courseSectionEntryTypeCode = courseSectionEntryTypeCode;
    }

    @Column(name = "CourseSectionExitTypeCode", length = 50)
    public String getCourseSectionExitTypeCode()
    {
        return this.courseSectionExitTypeCode;
    }

    public void setCourseSectionExitTypeCode(String courseSectionExitTypeCode)
    {
        this.courseSectionExitTypeCode = courseSectionExitTypeCode;
    }

    @Column(name = "ExitOrWithdrawalStatusCode", length = 50)
    public String getExitOrWithdrawalStatusCode()
    {
        return this.exitOrWithdrawalStatusCode;
    }

    public void setExitOrWithdrawalStatusCode(String exitOrWithdrawalStatusCode)
    {
        this.exitOrWithdrawalStatusCode = exitOrWithdrawalStatusCode;
    }

    @Column(name = "GradeLevelWhenCourseTakenCode", length = 50)
    public String getGradeLevelWhenCourseTakenCode()
    {
        return this.gradeLevelWhenCourseTakenCode;
    }

    public void setGradeLevelWhenCourseTakenCode(String gradeLevelWhenCourseTakenCode)
    {
        this.gradeLevelWhenCourseTakenCode = gradeLevelWhenCourseTakenCode;
    }

}