package org.ricone.api.mapping.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.ricone.api.model.core.*;
import org.ricone.api.model.xpress.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("XRosterMapper")
public class XRosterMapper {

    private final String localId = "LEA";
    private final String stateId = "SEA";

    public XRosterMapper() {
    }

    public XRostersResponse convert(List<CourseSection> instance)
    {
        List<XRoster> list = new ArrayList<>();
        for(CourseSection courseSection : instance)
        {
            XRoster xRoster = map(courseSection);
            if (xRoster != null)
            {
                list.add(xRoster);
            }
        }
        //List<XSchool> list = instance.stream().map(school -> map(school)).collect(Collectors.toList());

        XRostersResponse response = new XRostersResponse();
        XRosters xRosters = new XRosters();
        xRosters.setXRoster(list);

        response.setXRosters(xRosters);
        return response;
    }

    public XRosterResponse convert(CourseSection instance)
    {
        XRosterResponse response = new XRosterResponse();
        response.setXRoster(map(instance));
        return response;
    }


    public XRoster map(CourseSection instance)
    {
        XRoster xRoster = new XRoster();
        try {
            xRoster.setRefId(instance.getCourseSectionRefId());

            /* Course & School Data */
           /* if (instance.getCourse() != null) {
                xRoster.setCourseRefId(instance.getCourse().getCourseRefId());
                xRoster.setCourseTitle(instance.getCourse().getTitle());
                xRoster.setSubject(instance.getCourse().getSubjectCode());

                if (instance.getCourse().getSchool() != null) {
                    xRoster.setSchoolRefId(instance.getCourse().getSchool().getSchoolRefId());
                }
            }*/

            /* Calendar */
           /* if (instance.getSchoolCalendarSession() != null) {
                xRoster.setSessionCode(instance.getSchoolCalendarSession().getSessionTypeCode());

                if (instance.getSchoolCalendarSession().getSchoolCalendar() != null) {
                    xRoster.setSchoolYear(instance.getSchoolCalendarSession().getSchoolCalendar().getCalendarYear());
                    xRoster.setSchoolCalendarRefId(instance.getSchoolCalendarSession().getSchoolCalendar().getSchoolCalendarRefId());
                    //xRoster.setSchoolSectionId(null);
                }
            }*/

            /* Meeting Times */
           /* if(CollectionUtils.isNotEmpty(instance.getCourseSectionSchedules())) {
                List<MeetingTime> meetingTimeList = new ArrayList<>();
                for (CourseSectionSchedule courseSectionSchedule : instance.getCourseSectionSchedules()) {
                    MeetingTime meetingTime = mapMeetingTime(courseSectionSchedule, instance);
                    meetingTimeList.add(meetingTime);
                }

                if (CollectionUtils.isNotEmpty(meetingTimeList)) {
                    MeetingTimes meetingTimes = new MeetingTimes();
                    meetingTimes.setMeetingTime(meetingTimeList);
                    xRoster.setMeetingTimes(meetingTimes);
                }
            }*/


            /* Students */
            /*if(CollectionUtils.isNotEmpty(instance.getStudentCourseSections())) {
                List<StudentReference> studentList = new ArrayList<>();
                for (StudentCourseSection studentCourseSection : instance.getStudentCourseSections()) {
                    StudentReference staffPersonReference = mapStudent(studentCourseSection.getStudent());
                    studentList.add(staffPersonReference);
                }

                if (CollectionUtils.isNotEmpty(studentList)) {
                    Students students = new Students();
                    students.setStudentReference(studentList);
                    xRoster.setStudents(students);
                }
            }*/

            /* Staff */ //TODO: There is an error in here. Line 121
            /*if(CollectionUtils.isNotEmpty(instance.getStaffCourseSections()))
            {
                List<OtherStaff> otherStaffList = new ArrayList<>();
                for (StaffCourseSection staffCourseSection : instance.getStaffCourseSections())
                {
                    StaffPersonReference staffPersonReference = mapStaff(staffCourseSection.getStaff());
                    if (staffPersonReference != null) {
                        if (staffCourseSection.getTeacherOfRecord()) {
                            PrimaryStaff staff = new PrimaryStaff();
                            staff.setStaffPersonReference(staffPersonReference);
                            staff.setTeacherOfRecord(BooleanUtils.toStringTrueFalse(staffCourseSection.getTeacherOfRecord()));
                            staff.setPercentResponsible(bigDecimalConverter(staffCourseSection.getContributionPercentage()));

                            if (!staff.isEmptyObject()) {
                                xRoster.setPrimaryStaff(staff);
                            }
                        } else {
                            OtherStaff otherStaff = new OtherStaff();
                            otherStaff.setStaffPersonReference(staffPersonReference);
                            otherStaff.setTeacherOfRecord(BooleanUtils.toStringTrueFalse(staffCourseSection.getTeacherOfRecord()));
                            otherStaff.setPercentResponsible(bigDecimalConverter(staffCourseSection.getContributionPercentage()));

                            if (!otherStaff.isEmptyObject()) {
                                otherStaffList.add(otherStaff);
                            }
                        }
                    }

                }

                //Other Staff
                if (CollectionUtils.isNotEmpty(otherStaffList)) {
                    OtherStaffs otherStaffs = new OtherStaffs();
                    otherStaffs.setOtherStaff(otherStaffList);
                    xRoster.setOtherStaffs(otherStaffs);
                }
            }*/
        }
        catch(Exception e)
        {
            xRoster = null;
            e.printStackTrace();
        }
        return xRoster;
    }

    private MeetingTime mapMeetingTime(CourseSectionSchedule courseSectionSchedule, CourseSection instance)
    {
        MeetingTime meetingTime = new MeetingTime();
        meetingTime.setRoomNumber(courseSectionSchedule.getClassroomIdentifier());
        meetingTime.setSessionCode(instance.getSchoolCalendarSession().getSessionTypeCode());
        meetingTime.setTimeTableDay(null);
        meetingTime.setTimeTablePeriod(courseSectionSchedule.getClassPeriod());

        //Dates
        if(courseSectionSchedule.getClassBeginningTime() != null)
        {
            meetingTime.setClassBeginningTime(DateFormatUtils.format(courseSectionSchedule.getClassBeginningTime(), DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.getPattern()));
        }
        if(courseSectionSchedule.getClassEndingTime() != null)
        {
            meetingTime.setClassEndingTime(DateFormatUtils.format(courseSectionSchedule.getClassEndingTime(), DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.getPattern()));
        }

        //Class Meeting Days
        ClassMeetingDays classMeetingDays = mapClassMeetingDays(courseSectionSchedule.getClassMeetingDays());
        meetingTime.setClassMeetingDays(classMeetingDays);

        //Calendar
        if(instance.getSchoolCalendarSession() != null)
        {
            if(instance.getSchoolCalendarSession().getSchoolCalendar() != null)
            {
                meetingTime.setSchoolCalendarRefId(instance.getSchoolCalendarSession().getSchoolCalendar().getSchoolCalendarRefId());
            }
        }

        if(meetingTime.isEmptyObject())
        {
            return null;
        }
        return meetingTime;
    }

    private ClassMeetingDays mapClassMeetingDays(String meetingDays)
    {
        ClassMeetingDays classMeetingDays = new ClassMeetingDays();
        classMeetingDays.setBellScheduleDay(meetingDays);

        if(classMeetingDays.isEmptyObject())
        {
            return null;
        }
        return classMeetingDays;
    }


    private StaffPersonReference mapStaff(Staff staff)
    {
        StaffPersonReference staffPersonReference = new StaffPersonReference();
        staffPersonReference.setRefId(staff.getStaffRefId());
        staffPersonReference.setGivenName(staff.getFirstName());
        staffPersonReference.setFamilyName(staff.getLastName());

        for(StaffIdentifier id : staff.getStaffIdentifiers())
        {
            if(localId.equals(id.getIdentificationSystemCode()))
            {
                staffPersonReference.setLocalId(id.getStaffId());
                break;
            }
        }

        if(staffPersonReference.isEmptyObject())
        {
            return null;
        }
        return staffPersonReference;
    }


    private StudentReference mapStudent(Student student)
    {
        StudentReference studentReference = new StudentReference();
        studentReference.setRefId(student.getStudentRefId());
        studentReference.setFamilyName(student.getLastName());
        studentReference.setGivenName(student.getFirstName());

        for(StudentIdentifier id : student.getStudentIdentifiers())
        {
            if(localId.equals(id.getIdentificationSystemCode()))
            {
                studentReference.setLocalId(id.getStudentId());
                break;
            }
        }

        if(studentReference.isEmptyObject())
        {
            return null;
        }
        return studentReference;
    }

}
