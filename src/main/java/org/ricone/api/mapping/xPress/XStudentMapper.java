package org.ricone.api.mapping.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.ricone.api.model.core.*;
import org.ricone.api.model.xpress.*;
import org.ricone.api.util.Util;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("XStudentMapper")
public class XStudentMapper {

    private final String LOCAL_ID = "LEA";
    private final String STATE_ID = "SEA";
    private final String PRIMARY_ADDRESS_TYPE = "Mailing";
    private final String MEMBERSHIP_TYPE_CODE = "Home";
    private final String SYSTEM_TYPE_CODE = "CEDS";
    private final String ENTRY_TYPE = "Entry";
    private final String EXIT_TYPE = "Exit";

    public XStudentMapper() {
    }

    public XStudentsResponse convert(List<Student> instance)
    {
        List<XStudent> list = new ArrayList<>();
        for(Student student : instance)
        {
            XStudent xStudent = map(student);
            if (xStudent != null) {
                list.add(xStudent);
            }
        }

        XStudentsResponse response = new XStudentsResponse();
        XStudents xStudents = new XStudents();
        xStudents.setXStudent(list);

        response.setXStudents(xStudents);
        return response;
    }

    public XStudentResponse convert(Student instance)
    {
        XStudentResponse response = new XStudentResponse();
        XStudent xStudent = map(instance);
        if (xStudent != null) {
            response.setXStudent(xStudent);
        }
        return response;
    }

    public XStudent map(Student instance)
    {
        XStudent xStudent = new XStudent();
        xStudent.setRefId(instance.getStudentRefId());

        //Name
        Name name = mapName(instance);
        if(name != null) {
            xStudent.setName(name);
        }

        //Other Names
        List<Name> otherNameList = new ArrayList<>();
        for(StudentOtherName studentOtherName : instance.getStudentOtherNames())
        {
            Name otherName = mapOtherName(studentOtherName);
            if(otherName != null){
                otherNameList.add(otherName);
            }
        }
        if(CollectionUtils.isNotEmpty(otherNameList))
        {
            OtherNames otherNames = new OtherNames();
            otherNames.setName(otherNameList);
            xStudent.setOtherNames(otherNames);
        }

        //Email
        List<Email> emailList = new ArrayList<>();
        for(StudentEmail studentEmail : instance.getStudentEmails())
        {
            Email email = mapEmail(studentEmail);
            if(email != null) {
                if (studentEmail.getPrimaryEmailAddressIndicator()) {
                    xStudent.setEmail(email);
                } else {
                    emailList.add(email);
                }
            }
        }
        //Other Emails
        if(CollectionUtils.isNotEmpty(emailList))
        {
            OtherEmails otherEmails = new OtherEmails();
            otherEmails.setEmail(emailList);
            xStudent.setOtherEmails(otherEmails);
        }

        //Identifiers
        List<OtherId> otherIdList = new ArrayList<>();
        for(StudentIdentifier id : instance.getStudentIdentifiers())
        {
            if(LOCAL_ID.equals(id.getIdentificationSystemCode())) {
                xStudent.setLocalId(id.getStudentId());
            }
            else if(STATE_ID.equals(id.getIdentificationSystemCode())) {
                xStudent.setStateProvinceId(id.getStudentId());
            }
            else {
                OtherId otherId = mapOtherId(id);
                if(otherId != null) {
                    otherIdList.add(otherId);
                }
            }
        }
        //Other Identifiers
        if(CollectionUtils.isNotEmpty(otherIdList))
        {
            OtherIds otherIds = new OtherIds();
            otherIds.setOtherId(otherIdList);
            xStudent.setOtherIds(otherIds);
        }

        //Address
        for(StudentAddress studentAddress : instance.getStudentAddresses())
        {
            if(PRIMARY_ADDRESS_TYPE.equalsIgnoreCase(studentAddress.getAddressTypeCode()))
            {
                Address address = mapAddress(studentAddress);
                if(address != null) {
                    xStudent.setAddress(address);
                    break;
                }
            }
        }

        //Demographics
        Demographics demographics = mapDemographics(instance);
        if(demographics != null) {
            xStudent.setDemographics(demographics);
        }

        //Phone
        for(StudentTelephone studentTelephone : instance.getStudentTelephones())
        {
            if(studentTelephone.getPrimaryTelephoneNumberIndicator())
            {
                PhoneNumber phoneNumber = mapPhone(studentTelephone);
                if(phoneNumber != null) {
                    xStudent.setPhoneNumber(phoneNumber);
                    break;
                }
            }
        }

        //Enrollments
        List<Enrollment> enrollmentList = new ArrayList<>();
        for(StudentEnrollment studentEnrollment : instance.getStudentEnrollments())
        {
            Enrollment enrollment = mapEnrollment(studentEnrollment);
            if(enrollment != null)
            {
                if(MEMBERSHIP_TYPE_CODE.equalsIgnoreCase(studentEnrollment.getMembershipTypeCode())) {
                    xStudent.setEnrollment(enrollment);
                }
                else {
                    enrollmentList.add(enrollment);
                }
            }
        }
        //Other Enrollments
        if(CollectionUtils.isNotEmpty(enrollmentList))
        {
            OtherEnrollments otherEnrollments = new OtherEnrollments();
            otherEnrollments.setEnrollment(enrollmentList);
            xStudent.setOtherEnrollments(otherEnrollments);
        }

        //Academic Summary
        AcademicSummary academicSummary = mapAcademicSummary(instance.getStudentAcademicRecords());
        if(academicSummary != null) {
            xStudent.setAcademicSummary(academicSummary);
        }

        //Student Languages
        Languages languages = mapLanguages(instance.getStudentLanguages());
        if(languages != null) {
            xStudent.setLanguages(languages);
        }

        //Student Contacts
        StudentContacts studentContacts = mapStudentContacts(instance.getStudentContactRelationships());
        if(studentContacts != null) {
            xStudent.setStudentContacts(studentContacts);
        }

        return xStudent;
    }

    private OtherCode mapOtherCode(EntryExitCode entryExitCode) {
        OtherCode otherCode = new OtherCode();
        otherCode.setCodesetName(entryExitCode.getSystemTypeCode());
        otherCode.setOtherCodeValue(entryExitCode.getCode());

        if(otherCode.isEmptyObject())
        {
            return null;
        }
        return otherCode;
    }

    private AcademicSummary mapAcademicSummary(Set<StudentAcademicRecord> studentAcademicRecords) {
        List<StudentAcademicRecord> academicRecordList = new ArrayList<>(studentAcademicRecords); //Need list for sorting
        academicRecordList.sort(Comparator.comparing(StudentAcademicRecord::getAsOfDate)); //Sort by Latest Date
        StudentAcademicRecord academicRecord = academicRecordList.get(0); //Get Latest Date

        AcademicSummary academicSummary = new AcademicSummary();
        academicSummary.setClassRank(academicRecord.getHighSchoolStudentClassRank().toString());
        academicSummary.setCumulativeWeightedGpa(Util.bigDecimalConverter(academicRecord.getGradePointAverageCumulative()));
        academicSummary.setTermWeightedGpa(Util.bigDecimalConverter(academicRecord.getGradePointAverageGivenSession()));

        if(academicSummary.isEmptyObject())
        {
            return null;
        }
        return academicSummary;
    }

    private Demographics mapDemographics(Student instance) {
        Demographics demographics = new Demographics();
        demographics.setBirthDate(DateFormatUtils.format(instance.getBirthdate(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        demographics.setCountryOfBirth(instance.getCountryOfBirth());
        demographics.setHispanicLatinoEthnicity(BooleanUtils.toStringTrueFalse(instance.getHispanicLatinoEthnicity()));
        demographics.setSex(instance.getSexCode());
        demographics.setUsCitizenshipStatus(instance.getUsCitizenshipStatusCode());

        //Races
        Races races = mapRaces(instance.getStudentRaces());
        if(races != null) {
            demographics.setRaces(races);
        }

        if(demographics.isEmptyObject())
        {
            return null;
        }
        return demographics;
    }

    private Races mapRaces(Set<StudentRace> studentRaces) {
        Races races = new Races();
        for(StudentRace studentRace : studentRaces) {
            Race race = new Race();
            race.setRace(studentRace.getRaceCode());
            races.getRace().add(race);
        }

        if(races.isEmptyObject())
        {
            return null;
        }
        return races;
    }

    private Address mapAddress(StudentAddress studentAddress) {
        Address address = new Address();
        address.setAddressType(studentAddress.getAddressTypeCode());
        address.setLine1(studentAddress.getStreetNumberAndName());
        address.setLine2(studentAddress.getApartmentRoomOrSuiteNumber());
        address.setPostalCode(studentAddress.getPostalCode());
        address.setCity(studentAddress.getCity());
        address.setCountryCode(studentAddress.getCountryCode());
        address.setStateProvince(studentAddress.getStateCode());

        if(address.isEmptyObject())
        {
            return null;
        }
        return address;
    }

    private PhoneNumber mapPhone(StudentTelephone telephone) {
        PhoneNumber phone = new PhoneNumber();
        phone.setNumber(telephone.getTelephoneNumber());
        phone.setPhoneNumberType(telephone.getTelephoneNumberTypeCode());
        phone.setPrimaryIndicator(BooleanUtils.toStringTrueFalse(telephone.getPrimaryTelephoneNumberIndicator()));

        if(phone.isEmptyObject())
        {
            return null;
        }
        return phone;
    }

    private OtherId mapOtherId(StudentIdentifier id) {
        OtherId otherId = new OtherId();
        otherId.setId(id.getStudentId());
        otherId.setType(id.getIdentificationSystemCode());

        if(otherId.isEmptyObject())
        {
            return null;
        }
        return otherId;
    }

    private Name mapName(Student instance) {
        Name name = new Name();
        name.setFamilyName(instance.getLastName());
        name.setGivenName(instance.getFirstName());
        name.setMiddleName(instance.getMiddleName());
        name.setPrefix(instance.getPrefix());
        name.setSuffix(instance.getGenerationCode());
        name.setType(instance.getType());

        if(name.isEmptyObject())
        {
            return null;
        }
        return name;
    }

    private Name mapOtherName(StudentOtherName studentOtherName) {
        Name name = new Name();
        name.setFamilyName(studentOtherName.getLastName());
        name.setGivenName(studentOtherName.getFirstName());
        name.setMiddleName(studentOtherName.getMiddleName());
        name.setPrefix(studentOtherName.getPrefix());
        name.setSuffix(studentOtherName.getGenerationCode());
        name.setType(studentOtherName.getType());

        if(name.isEmptyObject())
        {
            return null;
        }
        return name;
    }

    private Email mapEmail(StudentEmail studentEmail) {
        Email email = new Email();
        email.setEmailAddress(studentEmail.getEmailAddress());
        email.setEmailType(studentEmail.getEmailTypeCode());

        if(email.isEmptyObject())
        {
            return null;
        }
        return email;
    }

    private StudentContacts mapStudentContacts(Set<StudentContactRelationship> relationships) {
        StudentContacts studentContacts = new StudentContacts();
        for(StudentContactRelationship relationship : relationships)
        {
            studentContacts.getContactPersonRefId().add(relationship.getStudent().getStudentRefId());
        }

        if(studentContacts.isEmptyObject())
        {
            return null;
        }
        return studentContacts;
    }

    private Languages mapLanguages(Set<StudentLanguage> studentLanguages) {
        Languages languages = new Languages();
        for(StudentLanguage studentLanguage : studentLanguages)
        {
            Language language = mapLanguage(studentLanguage);
            if(language != null) {
                languages.getLanguage().add(language);
            }
        }

        if(languages.isEmptyObject())
        {
            return null;
        }
        return languages;
    }

    private Language mapLanguage(StudentLanguage studentLanguage) {
        Language language = new Language();
        language.setCode(studentLanguage.getLanguageCode());
        language.setType(studentLanguage.getLanguageUseTypeCode());

        if(language.isEmptyObject())
        {
            return null;
        }
        return language;
    }

    private Enrollment mapEnrollment(StudentEnrollment studentEnrollment) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentSchoolAssociationRefId(null);
        enrollment.setHomeRoomNumber(studentEnrollment.getHomeroomIdentifier());
        enrollment.setGradeLevel(studentEnrollment.getCurrentGradeLevel());
        enrollment.setMembershipType(studentEnrollment.getMembershipTypeCode());
        enrollment.setProjectedGraduationYear(null);
        enrollment.setResponsibleSchoolType(studentEnrollment.getResponsibleSchoolTypeCode());
        enrollment.setEntryDate(DateFormatUtils.format(studentEnrollment.getEnrollmentEntryDate(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        enrollment.setExitDate(DateFormatUtils.format(studentEnrollment.getEnrollmentExitDate(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));

        if(studentEnrollment.getSchool() != null)
        {
            enrollment.setSchoolRefId(studentEnrollment.getSchool().getSchoolRefId());
            enrollment.setLeaRefId(studentEnrollment.getSchool().getLea().getLeaRefId());
        }

        //Entry Type
        EntryType entryType = mapEntryType(studentEnrollment.getEntryExitCodes());
        if(entryType != null) {
            enrollment.setEntryType(entryType);
        }

        //Exit Type
        ExitType exitType = mapExitType(studentEnrollment.getEntryExitCodes());
        if(exitType != null) {
            enrollment.setExitType(exitType);
        }

        //Home Room Teacher
        HomeRoomTeacher homeRoomTeacher = mapHomeRoomTeacher(studentEnrollment.getTeacher());
        if(homeRoomTeacher != null){
            enrollment.setHomeRoomTeacher(homeRoomTeacher);
        }

        //Counselor
        Counselor counselor = mapCounselor(studentEnrollment.getCounselor());
        if(counselor != null){
            enrollment.setCounselor(counselor);
        }

        if(enrollment.isEmptyObject())
        {
            return null;
        }
        return enrollment;
    }

    private Counselor mapCounselor(Staff staff) {
        Counselor counselor = new Counselor();
        counselor.setRefId(staff.getStaffRefId());
        counselor.setFamilyName(staff.getLastName());
        counselor.setGivenName(staff.getFirstName());

        for(StaffIdentifier id : staff.getStaffIdentifiers())
        {
            if(LOCAL_ID.equalsIgnoreCase(id.getIdentificationSystemCode())){
                counselor.setLocalId(id.getStaffId());
                break;
            }
        }

        if(counselor.isEmptyObject())
        {
            return null;
        }
        return counselor;
    }

    private HomeRoomTeacher mapHomeRoomTeacher(Staff staff) {
        HomeRoomTeacher homeRoomTeacher = new HomeRoomTeacher();
        homeRoomTeacher.setRefId(staff.getStaffRefId());
        homeRoomTeacher.setFamilyName(staff.getLastName());
        homeRoomTeacher.setGivenName(staff.getFirstName());

        for(StaffIdentifier id : staff.getStaffIdentifiers())
        {
            if(LOCAL_ID.equalsIgnoreCase(id.getIdentificationSystemCode())){
                homeRoomTeacher.setLocalId(id.getStaffId());
                break;
            }
        }

        if(homeRoomTeacher.isEmptyObject())
        {
            return null;
        }
        return homeRoomTeacher;
    }

    private EntryType mapEntryType(Set<EntryExitCode> entryExitCodes) {

        EntryType entryType = new EntryType();

        List<OtherCode> otherCodes = new ArrayList<>();
        for(EntryExitCode entryExitCode : entryExitCodes)
        {
            if(SYSTEM_TYPE_CODE.equalsIgnoreCase(entryExitCode.getSystemTypeCode()) && ENTRY_TYPE.equalsIgnoreCase(entryExitCode.getEntryExitType()))
            {
                entryType.setCode(entryExitCode.getCode());
            }
            else if(ENTRY_TYPE.equalsIgnoreCase(entryExitCode.getEntryExitType()))
            {
                OtherCode otherCode = mapOtherCode(entryExitCode);
                if(otherCode != null){
                    otherCodes.add(otherCode);
                }
            }
        }

        if(CollectionUtils.isNotEmpty(otherCodes))
        {
            entryType.setOtherCode(otherCodes);
        }

        if(entryType.isEmptyObject())
        {
            return null;
        }
        return entryType;
    }

    private ExitType mapExitType(Set<EntryExitCode> entryExitCodes) {
        ExitType exitType = new ExitType();

        List<OtherCode> otherCodes = new ArrayList<>();
        for(EntryExitCode entryExitCode : entryExitCodes)
        {
            if(SYSTEM_TYPE_CODE.equalsIgnoreCase(entryExitCode.getSystemTypeCode()) && EXIT_TYPE.equalsIgnoreCase(entryExitCode.getEntryExitType()))
            {
                exitType.setCode(entryExitCode.getCode());
            }
            else if(EXIT_TYPE.equalsIgnoreCase(entryExitCode.getEntryExitType()))
            {
                OtherCode otherCode = mapOtherCode(entryExitCode);
                if(otherCode != null){
                    otherCodes.add(otherCode);
                }
            }
        }

        if(CollectionUtils.isNotEmpty(otherCodes))
        {
            exitType.setOtherCode(otherCodes);
        }

        if(exitType.isEmptyObject())
        {
            return null;
        }
        return exitType;
    }

}