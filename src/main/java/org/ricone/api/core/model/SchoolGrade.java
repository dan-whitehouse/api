package org.ricone.api.core.model;

import javax.persistence.*;

@Entity
@Table(name = "schoolgrade")
public class SchoolGrade implements java.io.Serializable
{
    private static final long serialVersionUID = 7441130279606831553L;
    private String schoolGradeRefId;
    private School school;
    private String gradeLevelCode;

    public SchoolGrade()
    {
    }

    public SchoolGrade(String schoolGradeRefId, School school, String gradeLevelCode)
    {
        this.schoolGradeRefId = schoolGradeRefId;
        this.school = school;
        this.gradeLevelCode = gradeLevelCode;
    }

    @Id
    @Column(name = "SchoolGradeRefId", unique = true, nullable = false, length = 64)
    public String getSchoolGradeRefId()
    {
        return this.schoolGradeRefId;
    }

    public void setSchoolGradeRefId(String schoolGradeRefId)
    {
        this.schoolGradeRefId = schoolGradeRefId;
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

    @Column(name = "GradeLevelCode", nullable = false, length = 50)
    public String getGradeLevelCode()
    {
        return this.gradeLevelCode;
    }

    public void setGradeLevelCode(String gradeLevelCode)
    {
        this.gradeLevelCode = gradeLevelCode;
    }
}