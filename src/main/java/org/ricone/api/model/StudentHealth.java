package org.ricone.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "studenthealth")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentHealth implements java.io.Serializable
{
    private static final long serialVersionUID = 6287570933551222011L;
    private String studentHealthRefId;
    private Student student;
    private Date visionScreeningDate;
    private String visionScreeningStatusCode;
    private Date hearingScreeningDate;
    private String hearingScreeningStatusCode;
    private Date dentalScreeningDate;
    private String dentalScreeningStatusCode;
    private String healthInsuranceCoverageCode;
    private String dentalInsuranceCoverageTypeCode;
    private String medicalAlertIndicatorCode;
    private String healthScreeningEquipmentUsed;
    private String healthScreeningFollowUpRecommendation;

    public StudentHealth()
    {
    }

    public StudentHealth(String studentHealthRefId, Student student)
    {
        this.studentHealthRefId = studentHealthRefId;
        this.student = student;
    }

    public StudentHealth(String studentHealthRefId, Student student, Date visionScreeningDate, String visionScreeningStatusCode, Date hearingScreeningDate, String hearingScreeningStatusCode, Date dentalScreeningDate, String dentalScreeningStatusCode, String healthInsuranceCoverageCode, String dentalInsuranceCoverageTypeCode, String medicalAlertIndicatorCode, String healthScreeningEquipmentUsed, String healthScreeningFollowUpRecommendation)
    {
        this.studentHealthRefId = studentHealthRefId;
        this.student = student;
        this.visionScreeningDate = visionScreeningDate;
        this.visionScreeningStatusCode = visionScreeningStatusCode;
        this.hearingScreeningDate = hearingScreeningDate;
        this.hearingScreeningStatusCode = hearingScreeningStatusCode;
        this.dentalScreeningDate = dentalScreeningDate;
        this.dentalScreeningStatusCode = dentalScreeningStatusCode;
        this.healthInsuranceCoverageCode = healthInsuranceCoverageCode;
        this.dentalInsuranceCoverageTypeCode = dentalInsuranceCoverageTypeCode;
        this.medicalAlertIndicatorCode = medicalAlertIndicatorCode;
        this.healthScreeningEquipmentUsed = healthScreeningEquipmentUsed;
        this.healthScreeningFollowUpRecommendation = healthScreeningFollowUpRecommendation;
    }

    @Id
    @Column(name = "StudentHealthRefId", unique = true, nullable = false, length = 64)
    public String getStudentHealthRefId()
    {
        return this.studentHealthRefId;
    }

    public void setStudentHealthRefId(String studentHealthRefId)
    {
        this.studentHealthRefId = studentHealthRefId;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "VisionScreeningDate", length = 10)
    public Date getVisionScreeningDate()
    {
        return this.visionScreeningDate;
    }

    public void setVisionScreeningDate(Date visionScreeningDate)
    {
        this.visionScreeningDate = visionScreeningDate;
    }

    @Column(name = "VisionScreeningStatusCode", length = 50)
    public String getVisionScreeningStatusCode()
    {
        return this.visionScreeningStatusCode;
    }

    public void setVisionScreeningStatusCode(String visionScreeningStatusCode)
    {
        this.visionScreeningStatusCode = visionScreeningStatusCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "HearingScreeningDate", length = 10)
    public Date getHearingScreeningDate()
    {
        return this.hearingScreeningDate;
    }

    public void setHearingScreeningDate(Date hearingScreeningDate)
    {
        this.hearingScreeningDate = hearingScreeningDate;
    }

    @Column(name = "HearingScreeningStatusCode", length = 50)
    public String getHearingScreeningStatusCode()
    {
        return this.hearingScreeningStatusCode;
    }

    public void setHearingScreeningStatusCode(String hearingScreeningStatusCode)
    {
        this.hearingScreeningStatusCode = hearingScreeningStatusCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DentalScreeningDate", length = 10)
    public Date getDentalScreeningDate()
    {
        return this.dentalScreeningDate;
    }

    public void setDentalScreeningDate(Date dentalScreeningDate)
    {
        this.dentalScreeningDate = dentalScreeningDate;
    }

    @Column(name = "DentalScreeningStatusCode", length = 50)
    public String getDentalScreeningStatusCode()
    {
        return this.dentalScreeningStatusCode;
    }

    public void setDentalScreeningStatusCode(String dentalScreeningStatusCode)
    {
        this.dentalScreeningStatusCode = dentalScreeningStatusCode;
    }

    @Column(name = "HealthInsuranceCoverageCode", length = 50)
    public String getHealthInsuranceCoverageCode()
    {
        return this.healthInsuranceCoverageCode;
    }

    public void setHealthInsuranceCoverageCode(String healthInsuranceCoverageCode)
    {
        this.healthInsuranceCoverageCode = healthInsuranceCoverageCode;
    }

    @Column(name = "DentalInsuranceCoverageTypeCode", length = 50)
    public String getDentalInsuranceCoverageTypeCode()
    {
        return this.dentalInsuranceCoverageTypeCode;
    }

    public void setDentalInsuranceCoverageTypeCode(String dentalInsuranceCoverageTypeCode)
    {
        this.dentalInsuranceCoverageTypeCode = dentalInsuranceCoverageTypeCode;
    }

    @Column(name = "MedicalAlertIndicatorCode", length = 50)
    public String getMedicalAlertIndicatorCode()
    {
        return this.medicalAlertIndicatorCode;
    }

    public void setMedicalAlertIndicatorCode(String medicalAlertIndicatorCode)
    {
        this.medicalAlertIndicatorCode = medicalAlertIndicatorCode;
    }

    @Column(name = "HealthScreeningEquipmentUsed", length = 300)
    public String getHealthScreeningEquipmentUsed()
    {
        return this.healthScreeningEquipmentUsed;
    }

    public void setHealthScreeningEquipmentUsed(String healthScreeningEquipmentUsed)
    {
        this.healthScreeningEquipmentUsed = healthScreeningEquipmentUsed;
    }

    @Column(name = "HealthScreeningFollowUpRecommendation")
    public String getHealthScreeningFollowUpRecommendation()
    {
        return this.healthScreeningFollowUpRecommendation;
    }

    public void setHealthScreeningFollowUpRecommendation(String healthScreeningFollowUpRecommendation)
    {
        this.healthScreeningFollowUpRecommendation = healthScreeningFollowUpRecommendation;
    }
}