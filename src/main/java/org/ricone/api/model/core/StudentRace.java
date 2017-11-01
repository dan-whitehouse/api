package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentrace")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StudentRace implements java.io.Serializable
{
    private static final long serialVersionUID = -6465888681459728010L;
    private String studentRaceRefId;
    private Student student;
    private String raceCode;

    public StudentRace()
    {
    }

    public StudentRace(String studentRaceRefId, Student student)
    {
        this.studentRaceRefId = studentRaceRefId;
        this.student = student;
    }

    public StudentRace(String studentRaceRefId, Student student, String raceCode)
    {
        this.studentRaceRefId = studentRaceRefId;
        this.student = student;
        this.raceCode = raceCode;
    }

    @Id
    @Column(name = "StudentRaceRefId", unique = true, nullable = false, length = 64)
    public String getStudentRaceRefId()
    {
        return this.studentRaceRefId;
    }

    public void setStudentRaceRefId(String studentRaceRefId)
    {
        this.studentRaceRefId = studentRaceRefId;
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

    @Column(name = "RaceCode", length = 50)
    public String getRaceCode()
    {
        return this.raceCode;
    }

    public void setRaceCode(String raceCode)
    {
        this.raceCode = raceCode;
    }
}