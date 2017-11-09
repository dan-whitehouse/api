package org.ricone.api.core.dao;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactidentifier")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentContactIdentifier implements java.io.Serializable
{
    private static final long serialVersionUID = -6250333820678695905L;
    private String studentContactIdentifierRefId;
    private StudentContact studentContact;
    private String identificationSystemCode;
    private String studentContactId;

    public StudentContactIdentifier()
    {
    }

    public StudentContactIdentifier(String studentContactIdentifierRefId)
    {
        this.studentContactIdentifierRefId = studentContactIdentifierRefId;
    }

    public StudentContactIdentifier(String studentContactIdentifierRefId, StudentContact studentContact, String identificationSystemCode, String studentContactId)
    {
        this.studentContactIdentifierRefId = studentContactIdentifierRefId;
        this.studentContact = studentContact;
        this.identificationSystemCode = identificationSystemCode;
        this.studentContactId = studentContactId;
    }

    @Id
    @Column(name = "StudentContactIdentifierRefId", unique = true, nullable = false, length = 64)
    public String getStudentContactIdentifierRefId()
    {
        return this.studentContactIdentifierRefId;
    }

    public void setStudentContactIdentifierRefId(String studentContactIdentifierRefId)
    {
        this.studentContactIdentifierRefId = studentContactIdentifierRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentContactRefId")
    public StudentContact getStudentContact()
    {
        return this.studentContact;
    }

    public void setStudentContact(StudentContact studentContact)
    {
        this.studentContact = studentContact;
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

    @Column(name = "StudentContactId", length = 50)
    public String getStudentContactId()
    {
        return this.studentContactId;
    }

    public void setStudentContactId(String studentContactId)
    {
        this.studentContactId = studentContactId;
    }
}