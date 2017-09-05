package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class Course implements java.io.Serializable
{
    private static final long serialVersionUID = -7068659701898642886L;
    private String courseRefId;
    private School school;
    private String title;
    private String description;
    private String subjectCode;
    private String scedCourseCode;
    private String scedCourseLevelCode;
    private String scedCourseSubjectAreaCode;
    private Set<CourseSection> courseSections = new HashSet<CourseSection>(0);
    private Set<CourseIdentifier> courseIdentifiers = new HashSet<CourseIdentifier>(0);
    private Set<CourseGrade> courseGrades = new HashSet<CourseGrade>(0);
    private Boolean isDeleted = null;

    public Course()
    {
    }

    public Course(String courseRefId)
    {
        this.courseRefId = courseRefId;
    }

    public Course(String courseRefId, Boolean isDeleted)
    {
        this.courseRefId = courseRefId;
        this.isDeleted = isDeleted;
    }

    public Course(String courseRefId, School school)
    {
        this.courseRefId = courseRefId;
        this.school = school;
    }

    public Course(String courseRefId, School school, String title, String description, String subjectCode, String scedcourseCode, String scedcourseLevelCode, String scedcourseSubjectAreaCode, Set<CourseSection> coursesections, Set<CourseIdentifier> courseidentifiers, Set<CourseGrade> coursegrades)
    {
        this.courseRefId = courseRefId;
        this.school = school;
        this.title = title;
        this.description = description;
        this.subjectCode = subjectCode;
        this.scedCourseCode = scedcourseCode;
        this.scedCourseLevelCode = scedcourseLevelCode;
        this.scedCourseSubjectAreaCode = scedcourseSubjectAreaCode;
        this.courseSections = coursesections;
        this.courseIdentifiers = courseidentifiers;
        this.courseGrades = coursegrades;
    }

    @Id
    @Column(name = "CourseRefId", unique = true, nullable = false, length = 64)
    public String getCourseRefId()
    {
        return this.courseRefId;
    }

    public void setCourseRefId(String courseRefId)
    {
        this.courseRefId = courseRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SchoolRefId", nullable = false)
    public School getSchool()
    {
        return this.school;
    }

    public void setSchool(School school)
    {
        this.school = school;
    }

    @Column(name = "Title", length = 60)
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Column(name = "Description", length = 60)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Column(name = "SubjectCode", length = 50)
    public String getSubjectCode()
    {
        return this.subjectCode;
    }

    public void setSubjectCode(String subjectCode)
    {
        this.subjectCode = subjectCode;
    }

    @Column(name = "SCEDCourseCode", length = 5)
    public String getScedCourseCode()
    {
        return this.scedCourseCode;
    }

    public void setScedCourseCode(String scedcourseCode)
    {
        this.scedCourseCode = scedcourseCode;
    }

    @Column(name = "SCEDCourseLevelCode", length = 50)
    public String getScedCourseLevelCode()
    {
        return this.scedCourseLevelCode;
    }

    public void setScedCourseLevelCode(String scedcourseLevelCode)
    {
        this.scedCourseLevelCode = scedcourseLevelCode;
    }

    @Column(name = "SCEDCourseSubjectAreaCode", length = 50)
    public String getScedCourseSubjectAreaCode()
    {
        return this.scedCourseSubjectAreaCode;
    }

    public void setScedCourseSubjectAreaCode(String scedcourseSubjectAreaCode)
    {
        this.scedCourseSubjectAreaCode = scedcourseSubjectAreaCode;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    public Set<CourseSection> getCourseSections()
    {
        return this.courseSections;
    }

    public void setCourseSections(Set<CourseSection> coursesections)
    {
        this.courseSections = coursesections;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    public Set<CourseIdentifier> getCourseIdentifiers()
    {
        return this.courseIdentifiers;
    }

    public void setCourseIdentifiers(Set<CourseIdentifier> courseidentifiers)
    {
        this.courseIdentifiers = courseidentifiers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    public Set<CourseGrade> getCourseGrades()
    {
        return this.courseGrades;
    }

    public void setCourseGrades(Set<CourseGrade> coursegrades)
    {
        this.courseGrades = coursegrades;
    }

    @Transient
    public Boolean getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

}