package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentemail")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentEmail implements java.io.Serializable
{
    private static final long serialVersionUID = 3121641356609913131L;
    private String studentEmailRefId;
    private Student student;
    private String emailAddress;
    private String emailTypeCode;
    private Boolean primaryEmailAddressIndicator;

    public StudentEmail()
    {
    }

    public StudentEmail(String studentEmailRefId, Student student)
    {
        this.studentEmailRefId = studentEmailRefId;
        this.student = student;
    }

    public StudentEmail(String studentEmailRefId, Student student, String emailAddress, String emailTypeCode, Boolean primaryEmailAddressIndicator)
    {
        this.studentEmailRefId = studentEmailRefId;
        this.student = student;
        this.emailAddress = emailAddress;
        this.emailTypeCode = emailTypeCode;
        this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
    }

    @Id
    @Column(name = "StudentEmailRefId", unique = true, nullable = false, length = 64)
    public String getStudentEmailRefId()
    {
        return this.studentEmailRefId;
    }

    public void setStudentEmailRefId(String studentEmailRefId)
    {
        this.studentEmailRefId = studentEmailRefId;
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

    @Column(name = "EmailAddress", length = 128)
    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    @Column(name = "EmailTypeCode", length = 50)
    public String getEmailTypeCode()
    {
        return this.emailTypeCode;
    }

    public void setEmailTypeCode(String emailTypeCode)
    {
        this.emailTypeCode = emailTypeCode;
    }

    @Column(name = "PrimaryEmailAddressIndicator")
    public Boolean getPrimaryEmailAddressIndicator()
    {
        return this.primaryEmailAddressIndicator;
    }

    public void setPrimaryEmailAddressIndicator(Boolean primaryEmailAddressIndicator)
    {
        this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
    }
}
