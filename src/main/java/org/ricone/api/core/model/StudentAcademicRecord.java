package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "studentacademicrecord")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentAcademicRecord implements java.io.Serializable
{
    private static final long serialVersionUID = 6120294771566865543L;
    private String studentAcademicRecordRefId;
    private Student student;
    private Date asOfDate;
    private BigDecimal gradePointAverageCumulative;
    private BigDecimal gradePointAverageGivenSession;
    private Integer highSchoolStudentClassRank;

    public StudentAcademicRecord()
    {
    }

    public StudentAcademicRecord(String studentAcademicRecordRefId, Student student)
    {
        this.studentAcademicRecordRefId = studentAcademicRecordRefId;
        this.student = student;
    }

    public StudentAcademicRecord(String studentAcademicRecordRefId, Student student, Date asOfDate, BigDecimal gradePointAverageCumulative, BigDecimal gradePointAverageGivenSession, Integer highSchoolStudentClassRank)
    {
        this.studentAcademicRecordRefId = studentAcademicRecordRefId;
        this.student = student;
        this.asOfDate = asOfDate;
        this.gradePointAverageCumulative = gradePointAverageCumulative;
        this.gradePointAverageGivenSession = gradePointAverageGivenSession;
        this.highSchoolStudentClassRank = highSchoolStudentClassRank;
    }

    @Id
    @Column(name = "StudentAcademicRecordRefId", unique = true, nullable = false, length = 50)
    public String getStudentAcademicRecordRefId()
    {
        return this.studentAcademicRecordRefId;
    }

    public void setStudentAcademicRecordRefId(String studentAcademicRecordRefId)
    {
        this.studentAcademicRecordRefId = studentAcademicRecordRefId;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "AsOfDate", length = 10)
    public Date getAsOfDate()
    {
        return this.asOfDate;
    }

    public void setAsOfDate(Date asOfDate)
    {
        this.asOfDate = asOfDate;
    }

    @Column(name = "GradePointAverageCumulative", precision = 9, scale = 4)
    public BigDecimal getGradePointAverageCumulative()
    {
        return this.gradePointAverageCumulative;
    }

    public void setGradePointAverageCumulative(BigDecimal gradePointAverageCumulative)
    {
        this.gradePointAverageCumulative = gradePointAverageCumulative;
    }

    @Column(name = "GradePointAverageGivenSession", precision = 9, scale = 4)
    public BigDecimal getGradePointAverageGivenSession()
    {
        return this.gradePointAverageGivenSession;
    }

    public void setGradePointAverageGivenSession(BigDecimal gradePointAverageGivenSession)
    {
        this.gradePointAverageGivenSession = gradePointAverageGivenSession;
    }

    @Column(name = "HighSchoolStudentClassRank")
    public Integer getHighSchoolStudentClassRank()
    {
        return this.highSchoolStudentClassRank;
    }

    public void setHighSchoolStudentClassRank(Integer highSchoolStudentClassRank)
    {
        this.highSchoolStudentClassRank = highSchoolStudentClassRank;
    }
}