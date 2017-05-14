package org.ricone.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schoolidentifier")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class SchoolIdentifier implements java.io.Serializable
{
    private static final long serialVersionUID = 8276829540278448016L;
    private String schoolIdentifierRefId;
    private School school;
    private String identificationSystemCode;
    private String schoolId;

    public SchoolIdentifier()
    {
    }

    public SchoolIdentifier(String schoolIdentifierRefId, School school, String schoolId)
    {
        this.schoolIdentifierRefId = schoolIdentifierRefId;
        this.school = school;
        this.schoolId = schoolId;
    }

    public SchoolIdentifier(String schoolIdentifierRefId, School school, String identificationSystemCode, String schoolId)
    {
        this.schoolIdentifierRefId = schoolIdentifierRefId;
        this.school = school;
        this.identificationSystemCode = identificationSystemCode;
        this.schoolId = schoolId;
    }

    @Id
    @Column(name = "SchoolIdentifierRefId", unique = true, nullable = false, length = 64)
    public String getSchoolIdentifierRefId()
    {
        return this.schoolIdentifierRefId;
    }

    public void setSchoolIdentifierRefId(String schoolIdentifierRefId)
    {
        this.schoolIdentifierRefId = schoolIdentifierRefId;
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

    @Column(name = "IdentificationSystemCode", length = 50)
    public String getIdentificationSystemCode()
    {
        return this.identificationSystemCode;
    }

    public void setIdentificationSystemCode(String identificationSystemCode)
    {
        this.identificationSystemCode = identificationSystemCode;
    }

    @Column(name = "SchoolId", nullable = false, length = 50)
    public String getSchoolId()
    {
        return this.schoolId;
    }

    public void setSchoolId(String schoolId)
    {
        this.schoolId = schoolId;
    }
}