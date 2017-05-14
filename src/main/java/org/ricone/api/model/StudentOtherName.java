package org.ricone.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@Table(name = "studentothername")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentOtherName implements java.io.Serializable
{
    private static final long serialVersionUID = -2946697742300249084L;
    private String studentOtherNameRefId;
    private Student student;
    private String firstName;
    private String middleName;
    private String lastName;
    private String generationCode;
    private String prefix;
    private String type;

    public StudentOtherName()
    {
    }

    public StudentOtherName(String studentOtherNameRefId)
    {
        this.studentOtherNameRefId = studentOtherNameRefId;
    }

    public StudentOtherName(String studentOtherNameRefId, Student student, String firstName, String middleName, String lastName, String generationCode, String prefix, String type)
    {
        this.studentOtherNameRefId = studentOtherNameRefId;
        this.student = student;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.generationCode = generationCode;
        this.prefix = prefix;
        this.type = type;
    }

    @Id
    @Column(name = "StudentOtherNameRefId", unique = true, nullable = false, length = 64)
    public String getStudentOtherNameRefId()
    {
        return this.studentOtherNameRefId;
    }

    public void setStudentOtherNameRefId(String studentOtherNameRefId)
    {
        this.studentOtherNameRefId = studentOtherNameRefId;
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

    @Column(name = "FirstName", length = 35)
    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @Column(name = "MiddleName", length = 35)
    public String getMiddleName()
    {
        return this.middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    @Column(name = "LastName", length = 35)
    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Column(name = "GenerationCode", length = 10)
    public String getGenerationCode()
    {
        return this.generationCode;
    }

    public void setGenerationCode(String generationCode)
    {
        this.generationCode = generationCode;
    }

    @Column(name = "Prefix", length = 30)
    public String getPrefix()
    {
        return this.prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    @Column(name = "Type", length = 50)
    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}