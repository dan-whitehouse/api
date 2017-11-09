package org.ricone.api.core.dao;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactrelationship")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentContactRelationship implements java.io.Serializable
{
    private static final long serialVersionUID = 2782318822343339249L;
    private String studentContactRelationshipRefId;
    private StudentContact studentContact;
    private Student student;
    private String relationshipCode;
    private Boolean custodialRelationshipIndicator;
    private Boolean emergencyContactIndicator;
    private Integer contactPriorityNumber;
    private String contactRestrictions;
    private Boolean livesWithIndicator;
    private Boolean primaryContactIndicator;
    private Boolean financialResponsibilityIndicator;
    private Boolean communicationsIndicator;

    public StudentContactRelationship()
    {
    }

    public StudentContactRelationship(String studentContactRelationshipRefId, StudentContact studentContact, Student student)
    {
        this.studentContactRelationshipRefId = studentContactRelationshipRefId;
        this.studentContact = studentContact;
        this.student = student;
    }

    public StudentContactRelationship(String studentContactRelationshipRefId, StudentContact studentContact, Student student, String relationshipCode, Boolean custodialRelationshipIndicator, Boolean emergencyContactIndicator, Integer contactPriorityNumber, String contactRestrictions, Boolean livesWithIndicator, Boolean primaryContactIndicator, Boolean financialResponsibilityIndicator, Boolean communicationsIndicator)
    {
        this.studentContactRelationshipRefId = studentContactRelationshipRefId;
        this.studentContact = studentContact;
        this.student = student;
        this.relationshipCode = relationshipCode;
        this.custodialRelationshipIndicator = custodialRelationshipIndicator;
        this.emergencyContactIndicator = emergencyContactIndicator;
        this.contactPriorityNumber = contactPriorityNumber;
        this.contactRestrictions = contactRestrictions;
        this.livesWithIndicator = livesWithIndicator;
        this.primaryContactIndicator = primaryContactIndicator;
        this.financialResponsibilityIndicator = financialResponsibilityIndicator;
        this.communicationsIndicator = communicationsIndicator;
    }

    @Id
    @Column(name = "StudentContactRelationshipRefId", unique = true, nullable = false, length = 64)
    public String getStudentContactRelationshipRefId()
    {
        return this.studentContactRelationshipRefId;
    }

    public void setStudentContactRelationshipRefId(String studentContactRelationshipRefId)
    {
        this.studentContactRelationshipRefId = studentContactRelationshipRefId;
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

    @Column(name = "RelationshipCode", length = 50)
    public String getRelationshipCode()
    {
        return this.relationshipCode;
    }

    public void setRelationshipCode(String relationshipCode)
    {
        this.relationshipCode = relationshipCode;
    }

    @Column(name = "CustodialRelationshipIndicator")
    public Boolean getCustodialRelationshipIndicator()
    {
        return this.custodialRelationshipIndicator;
    }

    public void setCustodialRelationshipIndicator(Boolean custodialRelationshipIndicator)
    {
        this.custodialRelationshipIndicator = custodialRelationshipIndicator;
    }

    @Column(name = "EmergencyContactIndicator")
    public Boolean getEmergencyContactIndicator()
    {
        return this.emergencyContactIndicator;
    }

    public void setEmergencyContactIndicator(Boolean emergencyContactIndicator)
    {
        this.emergencyContactIndicator = emergencyContactIndicator;
    }

    @Column(name = "ContactPriorityNumber")
    public Integer getContactPriorityNumber()
    {
        return this.contactPriorityNumber;
    }

    public void setContactPriorityNumber(Integer contactPriorityNumber)
    {
        this.contactPriorityNumber = contactPriorityNumber;
    }

    @Column(name = "ContactRestrictions", length = 2000)
    public String getContactRestrictions()
    {
        return this.contactRestrictions;
    }

    public void setContactRestrictions(String contactRestrictions)
    {
        this.contactRestrictions = contactRestrictions;
    }

    @Column(name = "LivesWithIndicator")
    public Boolean getLivesWithIndicator()
    {
        return this.livesWithIndicator;
    }

    public void setLivesWithIndicator(Boolean livesWithIndicator)
    {
        this.livesWithIndicator = livesWithIndicator;
    }

    @Column(name = "PrimaryContactIndicator")
    public Boolean getPrimaryContactIndicator()
    {
        return this.primaryContactIndicator;
    }

    public void setPrimaryContactIndicator(Boolean primaryContactIndicator)
    {
        this.primaryContactIndicator = primaryContactIndicator;
    }

    @Column(name = "FinancialResponsibilityIndicator")
    public Boolean getFinancialResponsibilityIndicator()
    {
        return financialResponsibilityIndicator;
    }

    public void setFinancialResponsibilityIndicator(Boolean financialResponsibilityIndicator)
    {
        this.financialResponsibilityIndicator = financialResponsibilityIndicator;
    }

    @Column(name = "CommunicationsIndicator")
    public Boolean getCommunicationsIndicator()
    {
        return communicationsIndicator;
    }

    public void setCommunicationsIndicator(Boolean communicationsIndicator)
    {
        this.communicationsIndicator = communicationsIndicator;
    }
}