package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studenttelephone")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentTelephone implements java.io.Serializable
{
    private static final long serialVersionUID = 2259094736201878864L;
    private String studentPhoneRefId;
    private Student student;
    private String telephoneNumber;
    private Boolean primaryTelephoneNumberIndicator;
    private String telephoneNumberTypeCode;

    public StudentTelephone()
    {
    }

    public StudentTelephone(String studentPhoneRefId, Student student)
    {
        this.studentPhoneRefId = studentPhoneRefId;
        this.student = student;
    }

    public StudentTelephone(String studentPhoneRefId, Student student, String telephoneNumber, Boolean primaryTelephoneNumberIndicator, String telephoneNumberTypeCode)
    {
        this.studentPhoneRefId = studentPhoneRefId;
        this.student = student;
        this.telephoneNumber = telephoneNumber;
        this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
        this.telephoneNumberTypeCode = telephoneNumberTypeCode;
    }

    @Id
    @Column(name = "StudentPhoneRefId", unique = true, nullable = false, length = 64)
    public String getStudentPhoneRefId()
    {
        return this.studentPhoneRefId;
    }

    public void setStudentPhoneRefId(String studentPhoneRefId)
    {
        this.studentPhoneRefId = studentPhoneRefId;
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

    @Column(name = "TelephoneNumber", length = 24)
    public String getTelephoneNumber()
    {
        return this.telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

    @Column(name = "PrimaryTelephoneNumberIndicator")
    public Boolean getPrimaryTelephoneNumberIndicator()
    {
        return this.primaryTelephoneNumberIndicator;
    }

    public void setPrimaryTelephoneNumberIndicator(Boolean primaryTelephoneNumberIndicator)
    {
        this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
    }

    @Column(name = "TelephoneNumberTypeCode", length = 50)
    public String getTelephoneNumberTypeCode()
    {
        return this.telephoneNumberTypeCode;
    }

    public void setTelephoneNumberTypeCode(String telephoneNumberTypeCode)
    {
        this.telephoneNumberTypeCode = telephoneNumberTypeCode;
    }
}