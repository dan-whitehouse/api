package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentcontacttelephone")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentContactTelephone implements java.io.Serializable
{
    private static final long serialVersionUID = -6227475260415652096L;
    private String studentContactPhoneRefId;
    private StudentContact studentContact;
    private String telephoneNumber;
    private Boolean primaryTelephoneNumberIndicator;
    private String telephoneNumberTypeCode;

    public StudentContactTelephone()
    {
    }

    public StudentContactTelephone(String studentContactPhoneRefId, StudentContact studentContact)
    {
        this.studentContactPhoneRefId = studentContactPhoneRefId;
        this.studentContact = studentContact;
    }

    public StudentContactTelephone(String studentContactPhoneRefId, StudentContact studentContact, String telephoneNumber, Boolean primaryTelephoneNumberIndicator, String telephoneNumberTypeCode)
    {
        this.studentContactPhoneRefId = studentContactPhoneRefId;
        this.studentContact = studentContact;
        this.telephoneNumber = telephoneNumber;
        this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
        this.telephoneNumberTypeCode = telephoneNumberTypeCode;
    }

    @Id
    @Column(name = "StudentContactPhoneRefId", unique = true, nullable = false, length = 64)
    public String getStudentContactPhoneRefId()
    {
        return this.studentContactPhoneRefId;
    }

    public void setStudentContactPhoneRefId(String studentContactPhoneRefId)
    {
        this.studentContactPhoneRefId = studentContactPhoneRefId;
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