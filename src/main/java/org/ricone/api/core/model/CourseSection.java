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
@Table(name = "coursesection")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourseSection implements java.io.Serializable {
    private static final long serialVersionUID = 2424737254871734955L;
    private String courseSectionRefId;
    private SchoolCalendarSession schoolCalendarSession;
    private Course course;
    private String schoolSectionId;
    private String vendorSectionId;
    private Set<CourseSectionSchedule> courseSectionSchedules = new HashSet<CourseSectionSchedule>(0);
    private Set<StaffCourseSection> staffCourseSections = new HashSet<StaffCourseSection>(0);
    private Set<StudentCourseSection> studentCourseSections = new HashSet<StudentCourseSection>(0);

    public CourseSection() {
    }

    public CourseSection(String courseSectionRefId) {
        this.courseSectionRefId = courseSectionRefId;
    }


    public CourseSection(CourseSection courseSection, SchoolCalendarSession schoolcalendarsession, Course course, CourseSectionSchedule coursesectionschedules, StaffCourseSection staffcoursesections, StudentCourseSection studentcoursesections) {
        this.courseSectionRefId = courseSection.getCourseSectionRefId();
        this.schoolSectionId = courseSection.getSchoolSectionId();
        this.vendorSectionId = courseSection.getVendorSectionId();
        this.schoolCalendarSession = schoolcalendarsession;
        this.course = course;
        this.courseSectionSchedules.add(coursesectionschedules);
        this.staffCourseSections.add(staffcoursesections);
        this.studentCourseSections.add(studentcoursesections);
    }

    public CourseSection(String courseSectionRefId, SchoolCalendarSession schoolcalendarsession, Course course) {
        this.courseSectionRefId = courseSectionRefId;
        this.schoolCalendarSession = schoolcalendarsession;
        this.course = course;
    }

    public CourseSection(String courseSectionRefId, SchoolCalendarSession schoolcalendarsession, Course course, String schoolSectionId, String vendorSectionId, Set<CourseSectionSchedule> coursesectionschedules, Set<StaffCourseSection> staffcoursesections, Set<StudentCourseSection> studentcoursesections) {
        this.courseSectionRefId = courseSectionRefId;
        this.schoolCalendarSession = schoolcalendarsession;
        this.course = course;
        this.schoolSectionId = schoolSectionId;
        this.vendorSectionId = vendorSectionId;
        this.courseSectionSchedules = coursesectionschedules;
        this.staffCourseSections = staffcoursesections;
        this.studentCourseSections = studentcoursesections;
    }

    @Id
    @Column(name = "CourseSectionRefId", unique = true, nullable = false, length = 64)
    public String getCourseSectionRefId() {
        return this.courseSectionRefId;
    }

    public void setCourseSectionRefId(String courseSectionRefId) {
        this.courseSectionRefId = courseSectionRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SchoolCalendarSessionRefId", nullable = false)
    public SchoolCalendarSession getSchoolCalendarSession() {
        return this.schoolCalendarSession;
    }

    public void setSchoolCalendarSession(SchoolCalendarSession schoolcalendarsession) {
        this.schoolCalendarSession = schoolcalendarsession;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseRefId", nullable = false)
    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(name = "SchoolSectionId", length = 50)
    public String getSchoolSectionId() {
        return this.schoolSectionId;
    }

    public void setSchoolSectionId(String schoolSectionId) {
        this.schoolSectionId = schoolSectionId;
    }

    @Column(name = "VendorSectionId", length = 50)
    public String getVendorSectionId() {
        return this.vendorSectionId;
    }

    public void setVendorSectionId(String vendorSectionId) {
        this.vendorSectionId = vendorSectionId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseSection")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    public Set<CourseSectionSchedule> getCourseSectionSchedules() {
        return this.courseSectionSchedules;
    }

    public void setCourseSectionSchedules(Set<CourseSectionSchedule> coursesectionschedules) {
        this.courseSectionSchedules = coursesectionschedules;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseSection")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    public Set<StaffCourseSection> getStaffCourseSections() {
        return this.staffCourseSections;
    }

    public void setStaffCourseSections(Set<StaffCourseSection> staffcoursesections) {
        this.staffCourseSections = staffcoursesections;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseSection")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    public Set<StudentCourseSection> getStudentCourseSections() {
        return this.studentCourseSections;
    }

    public void setStudentCourseSections(Set<StudentCourseSection> studentcoursesections) {
        this.studentCourseSections = studentcoursesections;
    }

}