package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schoolcalendar")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
@Cacheable @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SchoolCalendar implements java.io.Serializable
{
    private static final long serialVersionUID = -4748613855950099628L;
    private String schoolCalendarRefId;
    private School school;
    private String calendarCode;
    private String calendarDescription;
    private String calendarYear;
    private Set<SchoolCalendarSession> schoolCalendarSessions = new HashSet<>(0);

    public SchoolCalendar()
    {
    }

    public SchoolCalendar(String schoolCalendarRefId)
    {
        this.schoolCalendarRefId = schoolCalendarRefId;
    }

    public SchoolCalendar(String schoolCalendarRefId, School school, String calendarCode, String calendarDescription, String calendarYear, Set<SchoolCalendarSession> schoolcalendarsessions)
    {
        this.schoolCalendarRefId = schoolCalendarRefId;
        this.school = school;
        this.calendarCode = calendarCode;
        this.calendarDescription = calendarDescription;
        this.calendarYear = calendarYear;
        this.schoolCalendarSessions = schoolcalendarsessions;
    }

    @Id
    @Column(name = "SchoolCalendarRefId", unique = true, nullable = false, length = 64)
    public String getSchoolCalendarRefId()
    {
        return this.schoolCalendarRefId;
    }
    public void setSchoolCalendarRefId(String schoolCalendarRefId)
    {
        this.schoolCalendarRefId = schoolCalendarRefId;
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

    @Column(name = "CalendarCode", length = 30)
    public String getCalendarCode()
    {
        return this.calendarCode;
    }
    public void setCalendarCode(String calendarCode)
    {
        this.calendarCode = calendarCode;
    }

    @Column(name = "CalendarDescription", nullable = false, length = 60)
    public String getCalendarDescription()
    {
        return this.calendarDescription;
    }
    public void setCalendarDescription(String calendarDescription)
    {
        this.calendarDescription = calendarDescription;
    }

    @Column(name = "CalendarYear", length = 4)
    public String getCalendarYear()
    {
        return this.calendarYear;
    }
    public void setCalendarYear(String calendarYear)
    {
        this.calendarYear = calendarYear;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolCalendar") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
    public Set<SchoolCalendarSession> getSchoolCalendarSessions()
    {
        return this.schoolCalendarSessions;
    }
    public void setSchoolCalendarSessions(Set<SchoolCalendarSession> schoolcalendarsessions) { this.schoolCalendarSessions = schoolcalendarsessions; }
}