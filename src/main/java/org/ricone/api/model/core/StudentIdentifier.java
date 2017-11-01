package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentidentifier")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentIdentifier implements java.io.Serializable
{
    private static final long serialVersionUID = -7539817796906734793L;
    private String studentIdentifierRefId;
    private Student student;
    private String identificationSystemCode;
    private String studentId;

    public StudentIdentifier()
    {
    }

    public StudentIdentifier(String studentIdentifierRefId)
    {
        this.studentIdentifierRefId = studentIdentifierRefId;
    }

    public StudentIdentifier(String studentIdentifierRefId, Student student, String identificationSystemCode, String studentId)
    {
        this.studentIdentifierRefId = studentIdentifierRefId;
        this.student = student;
        this.identificationSystemCode = identificationSystemCode;
        this.studentId = studentId;
    }

    @Id
    @Column(name = "StudentIdentifierRefId", unique = true, nullable = false, length = 64)
    public String getStudentIdentifierRefId()
    {
        return this.studentIdentifierRefId;
    }

    public void setStudentIdentifierRefId(String studentIdentifierRefId)
    {
        this.studentIdentifierRefId = studentIdentifierRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentRefId")
    public Student getStudent()
    {
        return this.student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
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

    @Column(name = "StudentId", length = 50)
    public String getStudentId()
    {
        return this.studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }
}