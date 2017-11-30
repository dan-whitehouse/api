package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coursesectionschedule")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class CourseSectionSchedule implements java.io.Serializable
{
    private static final long serialVersionUID = 3034286521311489646L;
    private String courseSectionScheduleRefId;
    private CourseSection courseSection;
    private String classMeetingDays;
    private Date classBeginningTime;
    private Date classEndingTime;
    private String classPeriod;
    private String timeDayIdentifier;
    private String classroomIdentifier;

    public CourseSectionSchedule()
    {
    }

    public CourseSectionSchedule(String courseSectionScheduleRefId)
    {
        this.courseSectionScheduleRefId = courseSectionScheduleRefId;
    }

    public CourseSectionSchedule(String courseSectionScheduleRefId, CourseSection courseSection, String classMeetingDays, Date classBeginningTime, Date classEndingTime, String classPeriod, String timeDayIdentifier, String classroomIdentifier)
    {
        this.courseSectionScheduleRefId = courseSectionScheduleRefId;
        this.courseSection = courseSection;
        this.classMeetingDays = classMeetingDays;
        this.classBeginningTime = classBeginningTime;
        this.classEndingTime = classEndingTime;
        this.classPeriod = classPeriod;
        this.timeDayIdentifier = timeDayIdentifier;
        this.classroomIdentifier = classroomIdentifier;
    }

    @Id
    @Column(name = "CourseSectionScheduleRefId", unique = true, nullable = false, length = 64)
    public String getCourseSectionScheduleRefId()
    {
        return this.courseSectionScheduleRefId;
    }

    public void setCourseSectionScheduleRefId(String courseSectionScheduleRefId)
    {
        this.courseSectionScheduleRefId = courseSectionScheduleRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseSectionRefId")
    public CourseSection getCourseSection()
    {
        return this.courseSection;
    }

    public void setCourseSection(CourseSection courseSection)
    {
        this.courseSection = courseSection;
    }

    @Column(name = "ClassMeetingDays", length = 60)
    public String getClassMeetingDays()
    {
        return this.classMeetingDays;
    }

    public void setClassMeetingDays(String classMeetingDays)
    {
        this.classMeetingDays = classMeetingDays;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "ClassBeginningTime", length = 8)
    public Date getClassBeginningTime()
    {
        return this.classBeginningTime;
    }

    public void setClassBeginningTime(Date classBeginningTime)
    {
        this.classBeginningTime = classBeginningTime;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "ClassEndingTime", length = 8)
    public Date getClassEndingTime()
    {
        return this.classEndingTime;
    }

    public void setClassEndingTime(Date classEndingTime)
    {
        this.classEndingTime = classEndingTime;
    }

    @Column(name = "ClassPeriod", length = 30)
    public String getClassPeriod()
    {
        return this.classPeriod;
    }

    public void setClassPeriod(String classPeriod)
    {
        this.classPeriod = classPeriod;
    }

    @Column(name = "TimeDayIdentifier", length = 30)
    public String getTimeDayIdentifier()
    {
        return this.timeDayIdentifier;
    }

    public void setTimeDayIdentifier(String timeDayIdentifier)
    {
        this.timeDayIdentifier = timeDayIdentifier;
    }

    @Column(name = "ClassroomIdentifier", length = 30)
    public String getClassroomIdentifier()
    {
        return this.classroomIdentifier;
    }

    public void setClassroomIdentifier(String classroomIdentifier)
    {
        this.classroomIdentifier = classroomIdentifier;
    }
}
