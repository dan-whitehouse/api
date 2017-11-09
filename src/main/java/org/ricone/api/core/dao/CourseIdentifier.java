package org.ricone.api.core.dao;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "courseidentifier")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class CourseIdentifier implements java.io.Serializable
{
    private static final long serialVersionUID = -2844248936102976357L;
    private String courseIdentifierRefId;
    private Course course;
    private String identificationSystemCode;
    private String courseId;

    public CourseIdentifier()
    {
    }

    public CourseIdentifier(String courseIdentifierRefId, Course course)
    {
        this.courseIdentifierRefId = courseIdentifierRefId;
        this.course = course;
    }

    public CourseIdentifier(String courseIdentifierRefId, Course course, String identificationSystemCode, String courseId)
    {
        this.courseIdentifierRefId = courseIdentifierRefId;
        this.course = course;
        this.identificationSystemCode = identificationSystemCode;
        this.courseId = courseId;
    }

    @Id
    @Column(name = "CourseIdentifierRefId", unique = true, nullable = false, length = 64)
    public String getCourseIdentifierRefId()
    {
        return this.courseIdentifierRefId;
    }

    public void setCourseIdentifierRefId(String courseIdentifierRefId)
    {
        this.courseIdentifierRefId = courseIdentifierRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseRefId", nullable = false)
    public Course getCourse()
    {
        return this.course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    @Column(name = "IdentificationSystemCode", length = 50)
    public String getIdentificationSystemCode()
    {
        return this.identificationSystemCode;
    }

    public void setIdentificationSystemCode(String identificationSystemCode)
    {
        this.identificationSystemCode = identificationSystemCode;
    }

    @Column(name = "CourseId", length = 50)
    public String getCourseId()
    {
        return this.courseId;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }
}