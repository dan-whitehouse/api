package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "studentenrollment")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StudentEnrollment implements java.io.Serializable {
    private static final long serialVersionUID = -7894349036867529032L;
    private String studentEnrollmentRefId;
    private Student student;
    private Staff counselor;
    private Staff teacher;
    private School school;
    private String entryGradeLevelCode;
    private String enrollmentStatusCode;
    private String entryTypeCode;
    private String exitGradeLevelCode;
    private String exitOrWithdrawalStatusCode;
    private String exitOrWithdrawalTypeCode;
    private Boolean displacedStudentStatus;
    private String endOfTermStatusCode;
    private String promotionReasonCode;
    private String foodServiceEligibilityCode;
    private Date firstEntryDateIntoUsschool;
    private String homeroomIdentifier;
    private String responsibleSchoolTypeCode;
    private String membershipTypeCode;
    private Date enrollmentEntryDate;
    private Date enrollmentExitDate;
    private String currentGradeLevel;
    private String studentSchoolAssociationRefId;
    private Set<EntryExitCode> entryExitCodes = new HashSet<>(0);

    public StudentEnrollment() {
    }

    public StudentEnrollment(String studentEnrollmentRefId, Student student, School school) {
        this.studentEnrollmentRefId = studentEnrollmentRefId;
        this.student = student;
        this.school = school;
    }

    public StudentEnrollment(String studentEnrollmentRefId, Student student, Staff counselor, Staff teacher, School school, String entryGradeLevelCode, String enrollmentStatusCode, String entryTypeCode, String exitGradeLevelCode, String exitOrWithdrawalStatusCode, String exitOrWithdrawalTypeCode, Boolean displacedStudentStatus, String endOfTermStatusCode, String promotionReasonCode, String foodServiceEligibilityCode, Date firstEntryDateIntoUsschool, String homeroomIdentifier, String responsibleSchoolTypeCode, String membershipTypeCode, Date enrollmentEntryDate, Date enrollmentExitDate, String currentGradeLevel, String studentSchoolAssociationRefId, Set<EntryExitCode> entryExitCodes) {
        this.studentEnrollmentRefId = studentEnrollmentRefId;
        this.student = student;
        this.counselor = counselor;
        this.teacher = teacher;
        this.school = school;
        this.entryGradeLevelCode = entryGradeLevelCode;
        this.enrollmentStatusCode = enrollmentStatusCode;
        this.entryTypeCode = entryTypeCode;
        this.exitGradeLevelCode = exitGradeLevelCode;
        this.exitOrWithdrawalStatusCode = exitOrWithdrawalStatusCode;
        this.exitOrWithdrawalTypeCode = exitOrWithdrawalTypeCode;
        this.displacedStudentStatus = displacedStudentStatus;
        this.endOfTermStatusCode = endOfTermStatusCode;
        this.promotionReasonCode = promotionReasonCode;
        this.foodServiceEligibilityCode = foodServiceEligibilityCode;
        this.firstEntryDateIntoUsschool = firstEntryDateIntoUsschool;
        this.homeroomIdentifier = homeroomIdentifier;
        this.responsibleSchoolTypeCode = responsibleSchoolTypeCode;
        this.membershipTypeCode = membershipTypeCode;
        this.enrollmentEntryDate = enrollmentEntryDate;
        this.enrollmentExitDate = enrollmentExitDate;
        this.currentGradeLevel = currentGradeLevel;
        this.studentSchoolAssociationRefId = studentSchoolAssociationRefId;
        this.entryExitCodes = entryExitCodes;
    }

    @Id
    @Column(name = "StudentEnrollmentRefId", unique = true, nullable = false, length = 64)
    public String getStudentEnrollmentRefId() {
        return this.studentEnrollmentRefId;
    }

    public void setStudentEnrollmentRefId(String studentEnrollmentRefId) {
        this.studentEnrollmentRefId = studentEnrollmentRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentRefId", nullable = false)
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CounselorRefId")
    public Staff getCounselor() {
        return this.counselor;
    }

    public void setCounselor(Staff staff) {
        this.counselor = staff;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HomeRoomTeacherRefId")
    public Staff getTeacher() {
        return teacher;
    }

    public void setTeacher(Staff teacher) {
        this.teacher = teacher;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SchoolRefId", nullable = false)
    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Column(name = "EntryGradeLevelCode", length = 50)
    public String getEntryGradeLevelCode() {
        return this.entryGradeLevelCode;
    }

    public void setEntryGradeLevelCode(String entryGradeLevelCode) {
        this.entryGradeLevelCode = entryGradeLevelCode;
    }

    @Column(name = "EnrollmentStatusCode", length = 50)
    public String getEnrollmentStatusCode() {
        return this.enrollmentStatusCode;
    }

    public void setEnrollmentStatusCode(String enrollmentStatusCode) {
        this.enrollmentStatusCode = enrollmentStatusCode;
    }

    @Column(name = "EntryTypeCode", length = 50)
    public String getEntryTypeCode() {
        return this.entryTypeCode;
    }

    public void setEntryTypeCode(String entryTypeCode) {
        this.entryTypeCode = entryTypeCode;
    }

    @Column(name = "ExitGradeLevelCode", length = 50)
    public String getExitGradeLevelCode() {
        return this.exitGradeLevelCode;
    }

    public void setExitGradeLevelCode(String exitGradeLevelCode) {
        this.exitGradeLevelCode = exitGradeLevelCode;
    }

    @Column(name = "ExitOrWithdrawalStatusCode", length = 50)
    public String getExitOrWithdrawalStatusCode() {
        return this.exitOrWithdrawalStatusCode;
    }

    public void setExitOrWithdrawalStatusCode(String exitOrWithdrawalStatusCode) {
        this.exitOrWithdrawalStatusCode = exitOrWithdrawalStatusCode;
    }

    @Column(name = "ExitOrWithdrawalTypeCode", length = 50)
    public String getExitOrWithdrawalTypeCode() {
        return this.exitOrWithdrawalTypeCode;
    }

    public void setExitOrWithdrawalTypeCode(String exitOrWithdrawalTypeCode) {
        this.exitOrWithdrawalTypeCode = exitOrWithdrawalTypeCode;
    }

    @Column(name = "DisplacedStudentStatus")
    public Boolean getDisplacedStudentStatus() {
        return this.displacedStudentStatus;
    }

    public void setDisplacedStudentStatus(Boolean displacedStudentStatus) {
        this.displacedStudentStatus = displacedStudentStatus;
    }

    @Column(name = "EndOfTermStatusCode", length = 50)
    public String getEndOfTermStatusCode() {
        return this.endOfTermStatusCode;
    }

    public void setEndOfTermStatusCode(String endOfTermStatusCode) {
        this.endOfTermStatusCode = endOfTermStatusCode;
    }

    @Column(name = "PromotionReasonCode", length = 50)
    public String getPromotionReasonCode() {
        return this.promotionReasonCode;
    }

    public void setPromotionReasonCode(String promotionReasonCode) {
        this.promotionReasonCode = promotionReasonCode;
    }

    @Column(name = "FoodServiceEligibilityCode", length = 50)
    public String getFoodServiceEligibilityCode() {
        return this.foodServiceEligibilityCode;
    }

    public void setFoodServiceEligibilityCode(String foodServiceEligibilityCode) {
        this.foodServiceEligibilityCode = foodServiceEligibilityCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FirstEntryDateIntoUSSchool", length = 10)
    public Date getFirstEntryDateIntoUsschool() {
        return this.firstEntryDateIntoUsschool;
    }

    public void setFirstEntryDateIntoUsschool(Date firstEntryDateIntoUsschool) {
        this.firstEntryDateIntoUsschool = firstEntryDateIntoUsschool;
    }

    @Column(name = "HomeRoomIdentifier", length = 30)
    public String getHomeroomIdentifier() {
        return this.homeroomIdentifier;
    }

    public void setHomeroomIdentifier(String homeRoomIdentifier) {
        this.homeroomIdentifier = homeRoomIdentifier;
    }

    @Column(name = "ResponsibleSchoolTypeCode", length = 50)
    public String getResponsibleSchoolTypeCode() {
        return this.responsibleSchoolTypeCode;
    }

    public void setResponsibleSchoolTypeCode(String responsibleSchoolTypeCode) {
        this.responsibleSchoolTypeCode = responsibleSchoolTypeCode;
    }

    @Column(name = "MembershipTypeCode", length = 50)
    public String getMembershipTypeCode() {
        return this.membershipTypeCode;
    }

    public void setMembershipTypeCode(String membershipTypeCode) {
        this.membershipTypeCode = membershipTypeCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EnrollmentEntryDate", length = 10)
    public Date getEnrollmentEntryDate() {
        return this.enrollmentEntryDate;
    }

    public void setEnrollmentEntryDate(Date enrollmentEntryDate) {
        this.enrollmentEntryDate = enrollmentEntryDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EnrollmentExitDate", length = 10)
    public Date getEnrollmentExitDate() {
        return this.enrollmentExitDate;
    }

    public void setEnrollmentExitDate(Date enrollmentExitDate) {
        this.enrollmentExitDate = enrollmentExitDate;
    }

    @Column(name = "CurrentGradeLevel", length = 50)
    public String getCurrentGradeLevel() {
        return this.currentGradeLevel;
    }

    public void setCurrentGradeLevel(String currentGradeLevel) {
        this.currentGradeLevel = currentGradeLevel;
    }

    @Column(name = "StudentSchoolAssociationRefId", length = 64)
    public String getStudentSchoolAssociationRefId() {
        return studentSchoolAssociationRefId;
    }

    public void setStudentSchoolAssociationRefId(String studentSchoolAssociationRefId) {
        this.studentSchoolAssociationRefId = studentSchoolAssociationRefId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentEnrollment")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    public Set<EntryExitCode> getEntryExitCodes() {
        return entryExitCodes;
    }

    public void setEntryExitCodes(Set<EntryExitCode> entryExitCodes) {
        this.entryExitCodes = entryExitCodes;
    }
}