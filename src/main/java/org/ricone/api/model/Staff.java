package org.ricone.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class Staff implements java.io.Serializable
{
    private static final long serialVersionUID = 1919054468978898526L;
    private String staffRefId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String generationCode;
    private String prefix;
    private Date birthdate;
    private String sexCode;
    private Boolean hispanicLatinoEthnicity;
    private String usCitizenshipStatusCode;
    private Long slk;
    private String type;
    private Set<StaffCourseSection> staffCourseSection = new HashSet<StaffCourseSection>(0);
    private Set<StaffIdentifier> staffIdentifiers = new HashSet<StaffIdentifier>(0);
    private Set<StudentEnrollment> studentEnrollments = new HashSet<StudentEnrollment>(0);
    private Set<StaffEmail> staffEmails = new HashSet<StaffEmail>(0);
    private Set<StaffAssignment> staffAssignments = new HashSet<StaffAssignment>(0);

    public Staff()
    {
    }

    public Staff(String staffRefId)
    {
        this.staffRefId = staffRefId;
    }

    public Staff(String staffRefId, String firstName, String middleName, String lastName, String generationCode, String prefix, Date birthdate, String sexCode, Boolean hispanicLatinoEthnicity, String uscitizenshipStatusCode, Long slk, String type, Set<StaffCourseSection> staffcoursesections, Set<StaffIdentifier> staffidentifiers, Set<StudentEnrollment> studentenrollments, Set<StaffEmail> staffemails, Set<StaffAssignment> staffassignments)
    {
        this.staffRefId = staffRefId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.generationCode = generationCode;
        this.prefix = prefix;
        this.birthdate = birthdate;
        this.sexCode = sexCode;
        this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
        this.usCitizenshipStatusCode = uscitizenshipStatusCode;
        this.slk = slk;
        this.type = type;
        this.staffCourseSection = staffcoursesections;
        this.staffIdentifiers = staffidentifiers;
        this.studentEnrollments = studentenrollments;
        this.staffEmails = staffemails;
        this.staffAssignments = staffassignments;
    }

    @Id
    @Column(name = "StaffRefId", unique = true, nullable = false, length = 64)
    public String getStaffRefId()
    {
        return this.staffRefId;
    }

    public void setStaffRefId(String staffRefId)
    {
        this.staffRefId = staffRefId;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    public Set<StaffCourseSection> getStaffCourseSections()
    {
        return this.staffCourseSection;
    }

    public void setStaffCourseSections(Set<StaffCourseSection> staffcoursesections)
    {
        this.staffCourseSection = staffcoursesections;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    public Set<StaffIdentifier> getStaffIdentifiers()
    {
        return this.staffIdentifiers;
    }

    public void setStaffIdentifiers(Set<StaffIdentifier> staffidentifiers)
    {
        this.staffIdentifiers = staffidentifiers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    public Set<StudentEnrollment> getStudentEnrollments()
    {
        return this.studentEnrollments;
    }

    public void setStudentEnrollments(Set<StudentEnrollment> studentenrollments)
    {
        this.studentEnrollments = studentenrollments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    public Set<StaffEmail> getStaffEmails()
    {
        return this.staffEmails;
    }

    public void setStaffEmails(Set<StaffEmail> staffemails)
    {
        this.staffEmails = staffemails;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    public Set<StaffAssignment> getStaffAssignments()
    {
        return this.staffAssignments;
    }

    public void setStaffAssignments(Set<StaffAssignment> staffassignments)
    {
        this.staffAssignments = staffassignments;
    }
}