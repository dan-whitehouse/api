package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "studentcontact")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
@Cacheable @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentContact implements java.io.Serializable
{
    private static final long serialVersionUID = -1949488111557559072L;
    private String studentContactRefId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String generationCode;
    private String prefix;
    private String sexCode;
    private String languageCode;
    private String languageUseTypeCode;
    private String type;
    private String employerType;
    private Set<StudentContactRelationship> studentContactRelationships = new HashSet<StudentContactRelationship>(0);
    private Set<StudentContactEmail> studentContactEmails = new HashSet<StudentContactEmail>(0);
    private Set<StudentContactAddress> studentContactAddresses = new HashSet<StudentContactAddress>(0);
    private Set<StudentContactTelephone> studentContactTelephones = new HashSet<StudentContactTelephone>(0);
    private Set<StudentContactIdentifier> studentContactIdentifiers = new HashSet<StudentContactIdentifier>(0);
    private Set<StudentContactOtherName> studentContactOtherNames = new HashSet<StudentContactOtherName>(0);

    public StudentContact()
    {
    }

    public StudentContact(String studentContactRefId)
    {
        this.studentContactRefId = studentContactRefId;
    }

    public StudentContact(String studentContactRefId, String firstName, String middleName, String lastName, String generationCode, String prefix, String sexCode, String languageCode, String languageUseTypeCode, String type, String employerType, Set<StudentContactRelationship> studentContactRelationships, Set<StudentContactEmail> studentContactEmails, Set<StudentContactAddress> studentContactAddresses, Set<StudentContactTelephone> studentContactTelephones, Set<StudentContactIdentifier> studentContactIdentifiers, Set<StudentContactOtherName> studentContactOtherNames)
    {
        this.studentContactRefId = studentContactRefId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.generationCode = generationCode;
        this.prefix = prefix;
        this.sexCode = sexCode;
        this.languageCode = languageCode;
        this.languageUseTypeCode = languageUseTypeCode;
        this.type = type;
        this.employerType = employerType;
        this.studentContactRelationships = studentContactRelationships;
        this.studentContactEmails = studentContactEmails;
        this.studentContactAddresses = studentContactAddresses;
        this.studentContactTelephones = studentContactTelephones;
        this.studentContactIdentifiers = studentContactIdentifiers;
        this.studentContactOtherNames = studentContactOtherNames;
    }

    @Id
    @Column(name = "StudentContactRefId", unique = true, nullable = false, length = 64)
    public String getStudentContactRefId()
    {
        return this.studentContactRefId;
    }
    public void setStudentContactRefId(String studentContactRefId)
    {
        this.studentContactRefId = studentContactRefId;
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

    @Column(name = "SexCode", length = 50)
    public String getSexCode()
    {
        return this.sexCode;
    }
    public void setSexCode(String sexCode)
    {
        this.sexCode = sexCode;
    }

    @Column(name = "LanguageCode", length = 50)
    public String getLanguageCode()
    {
        return this.languageCode;
    }
    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    @Column(name = "LanguageUseTypeCode", length = 50)
    public String getLanguageUseTypeCode()
    {
        return this.languageUseTypeCode;
    }
    public void setLanguageUseTypeCode(String languageUseTypeCode)
    {
        this.languageUseTypeCode = languageUseTypeCode;
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

    @Column(name = "EmployerType", length = 32)
    public String getEmployerType()
    {
        return employerType;
    }
    public void setEmployerType(String employerType)
    {
        this.employerType = employerType;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
    public Set<StudentContactRelationship> getStudentContactRelationships()
    {
        return this.studentContactRelationships;
    }
    public void setStudentContactRelationships(Set<StudentContactRelationship> studentcontactrelationships) { this.studentContactRelationships = studentcontactrelationships; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
    public Set<StudentContactEmail> getStudentContactEmails()
    {
        return this.studentContactEmails;
    }
    public void setStudentContactEmails(Set<StudentContactEmail> studentcontactemails) { this.studentContactEmails = studentcontactemails; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
    public Set<StudentContactAddress> getStudentContactAddresses()
    {
        return this.studentContactAddresses;
    }
    public void setStudentContactAddresses(Set<StudentContactAddress> studentcontactaddresses) { this.studentContactAddresses = studentcontactaddresses; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
    public Set<StudentContactTelephone> getStudentContactTelephones()
    {
        return this.studentContactTelephones;
    }
    public void setStudentContactTelephones(Set<StudentContactTelephone> studentcontacttelephones) { this.studentContactTelephones = studentcontacttelephones; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
    public Set<StudentContactIdentifier> getStudentContactIdentifiers()
    {
        return this.studentContactIdentifiers;
    }
    public void setStudentContactIdentifiers(Set<StudentContactIdentifier> studentcontactidentifiers) { this.studentContactIdentifiers = studentcontactidentifiers; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentContact") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
    public Set<StudentContactOtherName> getStudentContactOtherNames()
    {
        return studentContactOtherNames;
    }
    public void setStudentContactOtherNames(Set<StudentContactOtherName> studentContactOtherNames) { this.studentContactOtherNames = studentContactOtherNames; }
}
