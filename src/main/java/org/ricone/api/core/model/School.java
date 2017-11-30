package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "school")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
@Cacheable @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class School implements java.io.Serializable
{
    private static final long serialVersionUID = -4011491046830009714L;
    private String schoolRefId;
    private Lea lea;
    private String schoolName;
    private String streetNumberAndName;
    private String city;
    private String stateCode;
    private String postalCode;
    private String addressCountyName;
    private String addressType;
    private String line2;
    private String countryCode;
    private Set<SchoolGrade> schoolGrades = new HashSet<SchoolGrade>(0);
    private Set<SchoolTelephone> schoolTelephones = new HashSet<SchoolTelephone>(0);
    private Set<StaffAssignment> staffAssignments = new HashSet<StaffAssignment>(0);
    private Set<SchoolIdentifier> schoolIdentifiers = new HashSet<SchoolIdentifier>(0);
    private Set<SchoolCalendar> schoolCalendars = new HashSet<SchoolCalendar>(0);
    private Set<StudentEnrollment> studentEnrollments = new HashSet<StudentEnrollment>(0);
    private Set<Course> courses = new HashSet<Course>(0);

    public School()
    {
    }

    public School(String schoolRefId)
    {
        this.schoolRefId = schoolRefId;
    }

    public School(String schoolRefId, String schoolName)
    {
        this.schoolRefId = schoolRefId;
        this.schoolName = schoolName;
    }

    public School(String schoolRefId, Lea lea)
    {
        this.schoolRefId = schoolRefId;
        this.lea = lea;
    }

    public School(String schoolRefId, Lea lea, String schoolName, String streetNumberAndName, String city, String stateCode, String postalCode, String addressCountyName, String addressType, String line2, String countryCode, Set<SchoolGrade> schoolGrades, Set<SchoolTelephone> schoolTelephones, Set<StaffAssignment> staffAssignments, Set<SchoolIdentifier> schoolIdentifiers, Set<SchoolCalendar> schoolCalendars, Set<StudentEnrollment> studentEnrollments, Set<Course> courses)
    {
        this.schoolRefId = schoolRefId;
        this.lea = lea;
        this.schoolName = schoolName;
        this.streetNumberAndName = streetNumberAndName;
        this.city = city;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
        this.addressCountyName = addressCountyName;
        this.addressType = addressType;
        this.line2 = line2;
        this.countryCode = countryCode;
        this.schoolGrades = schoolGrades;
        this.schoolTelephones = schoolTelephones;
        this.staffAssignments = staffAssignments;
        this.schoolIdentifiers = schoolIdentifiers;
        this.schoolCalendars = schoolCalendars;
        this.studentEnrollments = studentEnrollments;
        this.courses = courses;
    }

    @Id
    @Column(name = "SchoolRefId", unique = true, nullable = false, length = 64)
    public String getSchoolRefId()
    {
        return this.schoolRefId;
    }
    public void setSchoolRefId(String schoolRefId)
    {
        this.schoolRefId = schoolRefId;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEARefId", nullable = false)
    public Lea getLea()
    {
        return this.lea;
    }
    public void setLea(Lea lea)
    {
        this.lea = lea;
    }

    @Column(name = "SchoolName", length = 60)
    public String getSchoolName()
    {
        return this.schoolName;
    }
    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    @Column(name = "StreetNumberAndName", length = 40)
    public String getStreetNumberAndName()
    {
        return this.streetNumberAndName;
    }
    public void setStreetNumberAndName(String streetNumberAndName)
    {
        this.streetNumberAndName = streetNumberAndName;
    }

    @Column(name = "City", length = 30)
    public String getCity()
    {
        return this.city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    @Column(name = "StateCode", length = 50)
    public String getStateCode()
    {
        return this.stateCode;
    }
    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }

    @Column(name = "PostalCode", length = 50)
    public String getPostalCode()
    {
        return this.postalCode;
    }
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    @Column(name = "AddressCountyName", length = 30)
    public String getAddressCountyName()
    {
        return this.addressCountyName;
    }
    public void setAddressCountyName(String addressCountyName)
    {
        this.addressCountyName = addressCountyName;
    }

    @Column(name = "AddressType", length = 32)
    public String getAddressType()
    {
        return addressType;
    }
    public void setAddressType(String addressType)
    {
        this.addressType = addressType;
    }

    @Column(name = "Line2", length = 40)
    public String getLine2()
    {
        return line2;
    }
    public void setLine2(String line2)
    {
        this.line2 = line2;
    }

    @Column(name = "CountryCode", length = 50)
    public String getCountryCode()
    {
        return countryCode;
    }
    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    public Set<SchoolGrade> getSchoolGrades()
    {
        return this.schoolGrades;
    }
    public void setSchoolGrades(Set<SchoolGrade> schoolgrades)
    {
        this.schoolGrades = schoolgrades;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    public Set<SchoolTelephone> getSchoolTelephones()
    {
        return this.schoolTelephones;
    }
    public void setSchoolTelephones(Set<SchoolTelephone> schooltelephones)
    {
        this.schoolTelephones = schooltelephones;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    public Set<StaffAssignment> getStaffAssignments()
    {
        return this.staffAssignments;
    }
    public void setStaffAssignments(Set<StaffAssignment> staffassignments)
    {
        this.staffAssignments = staffassignments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    public Set<SchoolIdentifier> getSchoolIdentifiers()
    {
        return this.schoolIdentifiers;
    }
    public void setSchoolIdentifiers(Set<SchoolIdentifier> schoolidentifiers){this.schoolIdentifiers = schoolidentifiers;}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    public Set<SchoolCalendar> getSchoolCalendars()
    {
        return this.schoolCalendars;
    }
    public void setSchoolCalendars(Set<SchoolCalendar> schoolcalendars)
    {
        this.schoolCalendars = schoolcalendars;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    public Set<StudentEnrollment> getStudentEnrollments()
    {
        return this.studentEnrollments;
    }
    public void setStudentEnrollments(Set<StudentEnrollment> studentenrollments){this.studentEnrollments = studentenrollments;}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    public Set<Course> getCourses()
    {
        return this.courses;
    }
    public void setCourses(Set<Course> courses)
    {
        this.courses = courses;
    }
}

