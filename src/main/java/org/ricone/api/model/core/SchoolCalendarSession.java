package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "schoolcalendarsession")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class SchoolCalendarSession implements java.io.Serializable
{
    private static final long serialVersionUID = -5166710493348669924L;
    private String schoolCalendarSessionRefId;
    private SchoolCalendar schoolCalendar;
    private String designator;
    private Date beginDate;
    private Date endDate;
    private String sessionTypeCode;
    private Long instructionalMinutes;
    private String code;
    private String description;
    private Boolean markingTermIndicator;
    private Boolean schedulingTermIndicator;
    private Boolean attendanceTermIndicator;
    private Integer daysInSession;
    private Date firstInstructionDate;
    private Date lastInstructionDate;
    private Integer minutesPerDay;
    private Date sessionStartTime;
    private Date sessionEndTime;
    private String linkedSessionCode;
    private Set<CourseSection> courseSections = new HashSet<>(0);

    public SchoolCalendarSession()
    {
    }

    public SchoolCalendarSession(String schoolCalendarSessionRefId)
    {
        this.schoolCalendarSessionRefId = schoolCalendarSessionRefId;
    }

    public SchoolCalendarSession(String schoolCalendarSessionRefId, SchoolCalendar schoolCalendar)
    {
        this.schoolCalendarSessionRefId = schoolCalendarSessionRefId;
        this.schoolCalendar = schoolCalendar;
    }

    public SchoolCalendarSession(String schoolCalendarSessionRefId, SchoolCalendar schoolCalendar, String designator, Date beginDate, Date endDate, String sessionTypeCode, Long instructionalMinutes, String code, String description, Boolean markingTermIndicator, Boolean schedulingTermIndicator, Boolean attendanceTermIndicator, Integer daysInSession, Date firstInstructionDate, Date lastInstructionDate, Integer minutesPerDay, Date sessionStartTime, Date sessionEndTime, String linkedSessionCode, Set<CourseSection> courseSections)
    {
        this.schoolCalendarSessionRefId = schoolCalendarSessionRefId;
        this.schoolCalendar = schoolCalendar;
        this.designator = designator;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.sessionTypeCode = sessionTypeCode;
        this.instructionalMinutes = instructionalMinutes;
        this.code = code;
        this.description = description;
        this.markingTermIndicator = markingTermIndicator;
        this.schedulingTermIndicator = schedulingTermIndicator;
        this.attendanceTermIndicator = attendanceTermIndicator;
        this.daysInSession = daysInSession;
        this.firstInstructionDate = firstInstructionDate;
        this.lastInstructionDate = lastInstructionDate;
        this.minutesPerDay = minutesPerDay;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.linkedSessionCode = linkedSessionCode;
        this.courseSections = courseSections;
    }

    @Id
    @Column(name = "SchoolCalendarSessionRefId", unique = true, nullable = false, length = 64)
    public String getSchoolCalendarSessionRefId()
    {
        return this.schoolCalendarSessionRefId;
    }

    public void setSchoolCalendarSessionRefId(String schoolCalendarSessionRefId)
    {
        this.schoolCalendarSessionRefId = schoolCalendarSessionRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SchoolCalendarRefId", nullable = false)
    public SchoolCalendar getSchoolCalendar()
    {
        return this.schoolCalendar;
    }

    public void setSchoolCalendar(SchoolCalendar schoolCalendar)
    {
        this.schoolCalendar = schoolCalendar;
    }

    @Column(name = "Designator", length = 7)
    public String getDesignator()
    {
        return this.designator;
    }

    public void setDesignator(String designator)
    {
        this.designator = designator;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BeginDate", length = 10)
    public Date getBeginDate()
    {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EndDate", length = 10)
    public Date getEndDate()
    {
        return this.endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    @Column(name = "SessionTypeCode", length = 50)
    public String getSessionTypeCode()
    {
        return this.sessionTypeCode;
    }

    public void setSessionTypeCode(String sessionTypeCode)
    {
        this.sessionTypeCode = sessionTypeCode;
    }

    @Column(name = "InstructionalMinutes")
    public Long getInstructionalMinutes()
    {
        return this.instructionalMinutes;
    }

    public void setInstructionalMinutes(Long instructionalMinutes)
    {
        this.instructionalMinutes = instructionalMinutes;
    }

    @Column(name = "Code", length = 30)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Column(name = "Description")
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Column(name = "MarkingTermIndicator")
    public Boolean getMarkingTermIndicator()
    {
        return this.markingTermIndicator;
    }

    public void setMarkingTermIndicator(Boolean markingTermIndicator)
    {
        this.markingTermIndicator = markingTermIndicator;
    }

    @Column(name = "SchedulingTermIndicator")
    public Boolean getSchedulingTermIndicator()
    {
        return this.schedulingTermIndicator;
    }

    public void setSchedulingTermIndicator(Boolean schedulingTermIndicator)
    {
        this.schedulingTermIndicator = schedulingTermIndicator;
    }

    @Column(name = "AttendanceTermIndicator")
    public Boolean getAttendanceTermIndicator()
    {
        return this.attendanceTermIndicator;
    }

    public void setAttendanceTermIndicator(Boolean attendanceTermIndicator)
    {
        this.attendanceTermIndicator = attendanceTermIndicator;
    }

    @Column(name = "DaysInSession")
    public Integer getDaysInSession()
    {
        return this.daysInSession;
    }

    public void setDaysInSession(Integer daysInSession)
    {
        this.daysInSession = daysInSession;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FirstInstructionDate", length = 10)
    public Date getFirstInstructionDate()
    {
        return this.firstInstructionDate;
    }

    public void setFirstInstructionDate(Date firstInstructionDate)
    {
        this.firstInstructionDate = firstInstructionDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LastInstructionDate", length = 10)
    public Date getLastInstructionDate()
    {
        return this.lastInstructionDate;
    }

    public void setLastInstructionDate(Date lastInstructionDate)
    {
        this.lastInstructionDate = lastInstructionDate;
    }

    @Column(name = "MinutesPerDay")
    public Integer getMinutesPerDay()
    {
        return this.minutesPerDay;
    }

    public void setMinutesPerDay(Integer minutesPerDay)
    {
        this.minutesPerDay = minutesPerDay;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "SessionStartTime", length = 8)
    public Date getSessionStartTime()
    {
        return this.sessionStartTime;
    }

    public void setSessionStartTime(Date sessionStartTime)
    {
        this.sessionStartTime = sessionStartTime;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "SessionEndTime", length = 8)
    public Date getSessionEndTime()
    {
        return this.sessionEndTime;
    }

    public void setSessionEndTime(Date sessionEndTime)
    {
        this.sessionEndTime = sessionEndTime;
    }

    @Column(name = "LinkedSessionCode", length = 30)
    public String getLinkedSessionCode()
    {
        return this.linkedSessionCode;
    }

    public void setLinkedSessionCode(String linkedSessionCode)
    {
        this.linkedSessionCode = linkedSessionCode;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolCalendarSession")
    public Set<CourseSection> getCourseSections()
    {
        return this.courseSections;
    }

    public void setCourseSections(Set<CourseSection> coursesections)
    {
        this.courseSections = coursesections;
    }

}