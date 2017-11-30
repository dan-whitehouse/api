package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "coursegrade")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class CourseGrade implements java.io.Serializable {
    private static final long serialVersionUID = 2650694715490007664L;
    private String courseGradeRefId;
    private Course course;
    private String gradeLevelCode;

    public CourseGrade() {
    }

    public CourseGrade(String courseGradeRefId, Course course, String gradeLevelCode) {
        this.courseGradeRefId = courseGradeRefId;
        this.course = course;
        this.gradeLevelCode = gradeLevelCode;
    }

    @Id
    @Column(name = "CourseGradeRefId", unique = true, nullable = false, length = 64)
    public String getCourseGradeRefId() {
        return this.courseGradeRefId;
    }

    public void setCourseGradeRefId(String courseGradeRefId) {
        this.courseGradeRefId = courseGradeRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseRefId", nullable = false)
    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(name = "GradeLevelCode", nullable = false, length = 50)
    public String getGradeLevelCode() {
        return this.gradeLevelCode;
    }

    public void setGradeLevelCode(String gradeLevelCode) {
        this.gradeLevelCode = gradeLevelCode;
    }
}
