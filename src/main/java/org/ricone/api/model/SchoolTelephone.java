package org.ricone.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schooltelephone")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class SchoolTelephone implements java.io.Serializable
{
    private static final long serialVersionUID = 2528704244410039161L;
    private String schoolPhoneRefId;
    private School school;
    private String telephoneNumber;
    private Boolean primaryTelephoneNumberIndicator;
    private String telephoneNumberTypeCode;

    public SchoolTelephone()
    {
    }

    public SchoolTelephone(String schoolPhoneRefId, School school)
    {
        this.schoolPhoneRefId = schoolPhoneRefId;
        this.school = school;
    }

    public SchoolTelephone(String schoolPhoneRefId, School school, String telephoneNumber, Boolean primaryTelephoneNumberIndicator, String telephoneNumberTypeCode)
    {
        this.schoolPhoneRefId = schoolPhoneRefId;
        this.school = school;
        this.telephoneNumber = telephoneNumber;
        this.primaryTelephoneNumberIndicator = primaryTelephoneNumberIndicator;
        this.telephoneNumberTypeCode = telephoneNumberTypeCode;
    }

    @Id
    @Column(name = "SchoolPhoneRefId", unique = true, nullable = false, length = 64)
    public String getSchoolPhoneRefId()
    {
        return this.schoolPhoneRefId;
    }

    public void setSchoolPhoneRefId(String schoolPhoneRefId)
    {
        this.schoolPhoneRefId = schoolPhoneRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SchoolRefId", nullable = false)
    public School getSchool()
    {
        return this.school;
    }

    public void setSchool(School school)
    {
        this.school = school;
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
