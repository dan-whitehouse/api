package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class Student implements java.io.Serializable
{
    private static final long serialVersionUID = -8205131726629327272L;
    private String studentRefId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String generationCode;
    private String prefix;
    private Date birthdate;
    private String sexCode;
    private Boolean hispanicLatinoEthnicity;
    private String usCitizenshipStatusCode;
    private String counselor;
    private String cohortGraduationYear;
    private String gradeLevelCode;
    private Long slk;
    private Date projectedGraduationDate;
    private String type;
    private String countryOfBirth;
    private Set<StudentRace> studentRaces = new HashSet<StudentRace>(0);
    private Set<StudentLanguage> studentLanguages = new HashSet<StudentLanguage>(0);
    private Set<StudentOtherName> studentOtherNames = new HashSet<StudentOtherName>(0);
    private Set<StudentContactRelationship> studentContactRelationships = new HashSet<StudentContactRelationship>(0);
    private Set<StudentAcademicRecord> studentAcademicRecords = new HashSet<StudentAcademicRecord>(0);
    private Set<StudentCourseSection> studentCourseSections = new HashSet<StudentCourseSection>(0);
    private Set<StudentIdentifier> studentIdentifiers = new HashSet<StudentIdentifier>(0);
    private Set<StudentHealth> studentHealths = new HashSet<StudentHealth>(0);
    private Set<StudentEnrollment> studentEnrollments = new HashSet<StudentEnrollment>(0);
    private Set<StudentTelephone> studentTelephones = new HashSet<StudentTelephone>(0);
    private Set<StudentEmail> studentEmails = new HashSet<StudentEmail>(0);
    private Set<StudentAddress> studentAddresses = new HashSet<StudentAddress>(0);

    public Student()
    {
    }

    public Student(String studentRefId)
    {
        this.studentRefId = studentRefId;
    }

    public Student(String studentRefId, String firstName, String middleName, String lastName, String generationCode, String prefix, Date birthdate, String sexCode, Boolean hispanicLatinoEthnicity, String usCitizenshipStatusCode, String counselor, String cohortGraduationYear, String gradeLevelCode, Long slk, String type, String countryOfBirth, Date projectedGraduationDate, Set<StudentRace> studentRaces, Set<StudentLanguage> studentLanguages, Set<StudentOtherName> studentOtherNames, Set<StudentContactRelationship> studentContactRelationships, Set<StudentAcademicRecord> studentAcademicRecords, Set<StudentCourseSection> studentCourseSections, Set<StudentIdentifier> studentIdentifiers, Set<StudentHealth> studentHealths, Set<StudentEnrollment> studentEnrollments, Set<StudentTelephone> studentTelephones, Set<StudentEmail> studentEmails, Set<StudentAddress> studentAddresses)
    {
        this.studentRefId = studentRefId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.generationCode = generationCode;
        this.prefix = prefix;
        this.birthdate = birthdate;
        this.sexCode = sexCode;
        this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
        this.usCitizenshipStatusCode = usCitizenshipStatusCode;
        this.counselor = counselor;
        this.cohortGraduationYear = cohortGraduationYear;
        this.gradeLevelCode = gradeLevelCode;
        this.slk = slk;
        this.type = type;
        this.countryOfBirth = countryOfBirth;
        this.projectedGraduationDate = projectedGraduationDate;
        this.studentRaces = studentRaces;
        this.studentLanguages = studentLanguages;
        this.studentOtherNames = studentOtherNames;
        this.studentContactRelationships = studentContactRelationships;
        this.studentAcademicRecords = studentAcademicRecords;
        this.studentCourseSections = studentCourseSections;
        this.studentIdentifiers = studentIdentifiers;
        this.studentHealths = studentHealths;
        this.studentEnrollments = studentEnrollments;
        this.studentTelephones = studentTelephones;
        this.studentEmails = studentEmails;
        this.studentAddresses = studentAddresses;
    }

    @Id
    @Column(name = "StudentRefId", unique = true, nullable = false, length = 64)
    public String getStudentRefId()
    {
        return this.studentRefId;
    }

    public void setStudentRefId(String studentRefId)
    {
        this.studentRefId = studentRefId;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "Birthdate", length = 10)
    public Date getBirthdate()
    {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
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

    @Column(name = "HispanicLatinoEthnicity")
    public Boolean getHispanicLatinoEthnicity()
    {
        return this.hispanicLatinoEthnicity;
    }

    public void setHispanicLatinoEthnicity(Boolean hispanicLatinoEthnicity)
    {
        this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
    }

    @Column(name = "USCitizenshipStatusCode", length = 50)
    public String getUsCitizenshipStatusCode()
    {
        return this.usCitizenshipStatusCode;
    }

    public void setUsCitizenshipStatusCode(String uscitizenshipStatusCode)
    {
        this.usCitizenshipStatusCode = uscitizenshipStatusCode;
    }

    @Column(name = "Counselor", length = 50)
    public String getCounselor()
    {
        return this.counselor;
    }

    public void setCounselor(String counselor)
    {
        this.counselor = counselor;
    }

    @Column(name = "CohortGraduationYear", length = 4)
    public String getCohortGraduationYear()
    {
        return this.cohortGraduationYear;
    }

    public void setCohortGraduationYear(String cohortGraduationYear)
    {
        this.cohortGraduationYear = cohortGraduationYear;
    }

    @Column(name = "GradeLevelCode", length = 50)
    public String getGradeLevelCode()
    {
        return this.gradeLevelCode;
    }

    public void setGradeLevelCode(String gradeLevelCode)
    {
        this.gradeLevelCode = gradeLevelCode;
    }

    @Column(name = "SLK")
    public Long getSlk()
    {
        return this.slk;
    }

    public void setSlk(Long slk)
    {
        this.slk = slk;
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

    @Column(name = "CountryOfBirth", length = 50)
    public String getCountryOfBirth()
    {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth)
    {
        this.countryOfBirth = countryOfBirth;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ProjectedGraduationDate", length = 10)
    public Date getProjectedGraduationDate()
    {
        return this.projectedGraduationDate;
    }

    public void setProjectedGraduationDate(Date projectedGraduationDate)
    {
        this.projectedGraduationDate = projectedGraduationDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentRace> getStudentRaces()
    {
        return this.studentRaces;
    }

    public void setStudentRaces(Set<StudentRace> studentraces)
    {
        this.studentRaces = studentraces;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentLanguage> getStudentLanguages()
    {
        return this.studentLanguages;
    }

    public void setStudentLanguages(Set<StudentLanguage> studentlanguages)
    {
        this.studentLanguages = studentlanguages;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentOtherName> getStudentOtherNames()
    {
        return this.studentOtherNames;
    }

    public void setStudentOtherNames(Set<StudentOtherName> studentothernames)
    {
        this.studentOtherNames = studentothernames;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentContactRelationship> getStudentContactRelationships()
    {
        return this.studentContactRelationships;
    }

    public void setStudentContactRelationships(Set<StudentContactRelationship> studentcontactrelationships)
    {
        this.studentContactRelationships = studentcontactrelationships;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentAcademicRecord> getStudentAcademicRecords()
    {
        return this.studentAcademicRecords;
    }

    public void setStudentAcademicRecords(Set<StudentAcademicRecord> studentacademicrecords)
    {
        this.studentAcademicRecords = studentacademicrecords;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentCourseSection> getStudentCourseSections()
    {
        return this.studentCourseSections;
    }

    public void setStudentCourseSections(Set<StudentCourseSection> studentcoursesections)
    {
        this.studentCourseSections = studentcoursesections;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentIdentifier> getStudentIdentifiers()
    {
        return this.studentIdentifiers;
    }

    public void setStudentIdentifiers(Set<StudentIdentifier> studentidentifiers)
    {
        this.studentIdentifiers = studentidentifiers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentHealth> getStudentHealths()
    {
        return this.studentHealths;
    }

    public void setStudentHealths(Set<StudentHealth> studenthealths)
    {
        this.studentHealths = studenthealths;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentEnrollment> getStudentEnrollments()
    {
        return this.studentEnrollments;
    }

    public void setStudentEnrollments(Set<StudentEnrollment> studentenrollments)
    {
        this.studentEnrollments = studentenrollments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentTelephone> getStudentTelephones()
    {
        return this.studentTelephones;
    }

    public void setStudentTelephones(Set<StudentTelephone> studenttelephones)
    {
        this.studentTelephones = studenttelephones;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentEmail> getStudentEmails()
    {
        return this.studentEmails;
    }

    public void setStudentEmails(Set<StudentEmail> studentemails)
    {
        this.studentEmails = studentemails;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<StudentAddress> getStudentAddresses()
    {
        return this.studentAddresses;
    }

    public void setStudentAddresses(Set<StudentAddress> studentaddresses)
    {
        this.studentAddresses = studentaddresses;
    }
}