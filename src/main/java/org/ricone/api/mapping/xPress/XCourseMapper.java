package org.ricone.api.mapping.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.model.core.*;
import org.ricone.api.model.xpress.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("XCourseMapper")
public class XCourseMapper {

    public XCourseMapper() {
    }

    public XCoursesResponse convert(List<Course> instance)
    {
        List<XCourse> list = new ArrayList<>();
        for(Course course : instance)
        {
            XCourse xCourse = map(course);
            if (xCourse != null)
            {
                list.add(xCourse);
            }
        }

        XCoursesResponse response = new XCoursesResponse();
        XCourses xCourses = new XCourses();
        xCourses.setXCourse(list);

        response.setXCourses(xCourses);
        return response;
    }

    public XCourseResponse convert(Course instance)
    {
        XCourseResponse response = new XCourseResponse();
        response.setXCourse(map(instance));
        return response;
    }


    public XCourse map(Course instance)
    {
        XCourse xCourse = new XCourse();
        xCourse.setRefId(instance.getCourseRefId());
        xCourse.setCourseTitle(instance.getTitle());
        xCourse.setSubject(instance.getSubjectCode());
        xCourse.setDescription(instance.getDescription());
        xCourse.setSchoolCourseId(instance.getCourseRefId());

        if(instance.getSchool() != null)
        {
            xCourse.setSchoolRefId(instance.getSchool().getSchoolRefId());

            if(instance.getSchool().getLea() != null)
            {
                xCourse.setLeaCourseId(instance.getSchool().getLea().getLeaRefId());
            }
        }

        xCourse.setScedCourseCode(instance.getScedCourseCode());
        xCourse.setScedCourseLevelCode(instance.getScedCourseLevelCode());
        xCourse.setScedCourseSubjectAreaCode(instance.getScedCourseSubjectAreaCode());


        //Identifiers
        List<OtherId> otherIdList = new ArrayList<>();
        for(CourseIdentifier id : instance.getCourseIdentifiers())
        {
            OtherId otherId = mapOtherId(id);
            otherIdList.add(otherId);
        }

        //Other Identifiers
        if(CollectionUtils.isNotEmpty(otherIdList))
        {
            OtherIds otherIds = new OtherIds();
            otherIds.setOtherId(otherIdList);
            xCourse.setOtherIds(otherIds);
        }

        //Applicable Education Levels
        ApplicableEducationLevels applicableEducationLevels = mapApplicableEducationLevels(instance.getCourseGrades());
        xCourse.setApplicableEducationLevels(applicableEducationLevels);

        return xCourse;
    }

    private ApplicableEducationLevels mapApplicableEducationLevels(Set<CourseGrade> courseGrades)
    {
        ApplicableEducationLevels applicableEducationLevels = new ApplicableEducationLevels();
        for(CourseGrade grade : courseGrades)
        {
            applicableEducationLevels.getApplicableEducationLevel().add(grade.getGradeLevelCode());
        }

        if(applicableEducationLevels.isEmptyObject())
        {
            return null;
        }
        return applicableEducationLevels;
    }

    private OtherId mapOtherId(CourseIdentifier id)
    {
        OtherId otherId = new OtherId();
        otherId.setId(id.getCourseId());
        otherId.setType(id.getIdentificationSystemCode());

        if(otherId.isEmptyObject())
        {
            return null;
        }
        return otherId;
    }
}