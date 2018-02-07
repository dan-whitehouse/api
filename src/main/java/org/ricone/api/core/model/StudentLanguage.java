package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentlanguage")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StudentLanguage implements java.io.Serializable {
    private static final long serialVersionUID = -5261716524080403446L;
    private String studentLanguageRefId;
    private Student student;
    private String languageCode;
    private String languageUseTypeCode;

    public StudentLanguage() {
    }

    public StudentLanguage(String studentLanguageRefId, Student student) {
        this.studentLanguageRefId = studentLanguageRefId;
        this.student = student;
    }

    public StudentLanguage(String studentLanguageRefId, Student student, String languageCode, String languageUseTypeCode) {
        this.studentLanguageRefId = studentLanguageRefId;
        this.student = student;
        this.languageCode = languageCode;
        this.languageUseTypeCode = languageUseTypeCode;
    }

    @Id
    @Column(name = "StudentLanguageRefId", unique = true, nullable = false, length = 64)
    public String getStudentLanguageRefId() {
        return this.studentLanguageRefId;
    }

    public void setStudentLanguageRefId(String studentLanguageRefId) {
        this.studentLanguageRefId = studentLanguageRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentRefId", nullable = false)
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Column(name = "LanguageCode", length = 50)
    public String getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Column(name = "LanguageUseTypeCode", length = 50)
    public String getLanguageUseTypeCode() {
        return this.languageUseTypeCode;
    }

    public void setLanguageUseTypeCode(String languageUseTypeCode) {
        this.languageUseTypeCode = languageUseTypeCode;
    }
}