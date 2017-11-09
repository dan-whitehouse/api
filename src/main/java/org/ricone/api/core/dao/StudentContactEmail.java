package org.ricone.api.core.dao;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactemail")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentContactEmail implements java.io.Serializable
{
    private static final long serialVersionUID = -2097058017964142612L;
    private String studentContactEmailRefId;
    private StudentContact studentContact;
    private String emailAddress;
    private String emailTypeCode;
    private Boolean primaryEmailAddressIndicator;

    public StudentContactEmail()
    {
    }

    public StudentContactEmail(String studentContactEmailRefId, StudentContact studentContact)
    {
        this.studentContactEmailRefId = studentContactEmailRefId;
        this.studentContact = studentContact;
    }

    public StudentContactEmail(String studentContactEmailRefId, StudentContact studentContact, String emailAddress, String emailTypeCode, Boolean primaryEmailAddressIndicator)
    {
        this.studentContactEmailRefId = studentContactEmailRefId;
        this.studentContact = studentContact;
        this.emailAddress = emailAddress;
        this.emailTypeCode = emailTypeCode;
        this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
    }

    @Id
    @Column(name = "StudentContactEmailRefId", unique = true, nullable = false, length = 64)
    public String getStudentContactEmailRefId()
    {
        return this.studentContactEmailRefId;
    }

    public void setStudentContactEmailRefId(String studentContactEmailRefId)
    {
        this.studentContactEmailRefId = studentContactEmailRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentContactRefId", nullable = false)
    public StudentContact getStudentContact()
    {
        return this.studentContact;
    }

    public void setStudentContact(StudentContact studentContact)
    {
        this.studentContact = studentContact;
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