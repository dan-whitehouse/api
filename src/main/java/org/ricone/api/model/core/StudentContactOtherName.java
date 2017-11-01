package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactothername")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentContactOtherName implements java.io.Serializable
{

    private static final long serialVersionUID = -3879579464081349767L;
    private String studentContactOtherNameRefId;
    private StudentContact studentContact;
    private String firstName;
    private String middleName;
    private String lastName;
    private String generationCode;
    private String prefix;
    private String type;

    public StudentContactOtherName()
    {
    }

    public StudentContactOtherName(String studentContactOtherNameRefId)
    {
        this.studentContactOtherNameRefId = studentContactOtherNameRefId;
    }

    public StudentContactOtherName(String studentContactOtherNameRefId, StudentContact studentContact, String firstName, String middleName, String lastName, String generationCode, String prefix, String type)
    {
        this.studentContactOtherNameRefId = studentContactOtherNameRefId;
        this.studentContact = studentContact;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.generationCode = generationCode;
        this.prefix = prefix;
        this.type = type;
    }

    @Id
    @Column(name = "StudentContactOtherNameRefId", unique = true, nullable = false, length = 64)
    public String getStudentContactOtherNameRefId()
    {
        return studentContactOtherNameRefId;
    }

    public void setStudentContactOtherNameRefId(String studentContactOtherNameRefId)
    {
        this.studentContactOtherNameRefId = studentContactOtherNameRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentContactRefId", nullable = false)
    public StudentContact getStudentContact()
    {
        return studentContact;
    }

    public void setStudentContact(StudentContact studentContact)
    {
        this.studentContact = studentContact;
    }

    @Column(name = "FirstName", length = 35)
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @Column(name = "MiddleName", length = 35)
    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    @Column(name = "LastName", length = 35)
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Column(name = "GenerationCode", length = 10)
    public String getGenerationCode()
    {
        return generationCode;
    }

    public void setGenerationCode(String generationCode)
    {
        this.generationCode = generationCode;
    }

    @Column(name = "Prefix", length = 30)
    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    @Column(name = "Type", length = 32)
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}