package org.ricone.api.mapping.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.ricone.api.model.core.*;
import org.ricone.api.model.xpress.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("XStaffMapper")
public class XStaffMapper {

    private final String LOCAL_ID = "LEA";
    private final String STATE_ID = "SEA";

    public XStaffMapper() {
    }

    public XStaffsResponse convert(List<Staff> instance)
    {
        List<XStaff> list = new ArrayList<>();
        for(Staff staff : instance)
        {
            XStaff xStaff = map(staff);
            if (xStaff != null) {
                list.add(xStaff);
            }
        }

        XStaffsResponse response = new XStaffsResponse();
        XStaffs xStaffs = new XStaffs();
        xStaffs.setXStaff(list);

        response.setXStaffs(xStaffs);
        return response;
    }

    public XStaffResponse convert(Staff instance)
    {
        XStaffResponse response = new XStaffResponse();
        XStaff xStaff = map(instance);
        if (xStaff != null) {
            response.setXStaff(map(instance));;
        }
        return response;
    }


    public XStaff map(Staff instance)
    {
        XStaff xStaff = new XStaff();
        xStaff.setRefId(instance.getStaffRefId());
        xStaff.setSex(instance.getSexCode());

        //Name
        Name name = mapName(instance);
        if(name != null) {
            xStaff.setName(name);
        }

        //Email
        for(StaffEmail staffEmail : instance.getStaffEmails())
        {
            if(staffEmail.getPrimaryEmailAddressIndicator())
            {
                Email email = mapEmail(staffEmail);
                if(staffEmail != null)
                {
                    xStaff.setEmail(email);
                    break;
                }
            }
        }

        //Identifiers
        List<OtherId> otherIdList = new ArrayList<>();
        for(StaffIdentifier id : instance.getStaffIdentifiers())
        {
            if(LOCAL_ID.equals(id.getIdentificationSystemCode())) {
                xStaff.setLocalId(id.getStaffId());
            }
            else if(STATE_ID.equals(id.getIdentificationSystemCode())) {
                xStaff.setStateProvinceId(id.getStaffId());
            }
            else
            {
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
            xStaff.setOtherIds(otherIds);
        }

        //Assignments
        List<StaffPersonAssignment> assignmentList = new ArrayList<>();
        for(StaffAssignment assignment : instance.getStaffAssignments())
        {
            if(assignment.getPrimaryAssignment())
            {
                PrimaryAssignment primaryAssignment = mapPrimaryAssignment(assignment);
                if(primaryAssignment != null){
                    xStaff.setPrimaryAssignment(primaryAssignment);
                }
            }
            else
            {
                StaffPersonAssignment staffPersonAssignment = mapOtherAssignment(assignment);
                if(staffPersonAssignment != null){
                    assignmentList.add(staffPersonAssignment);
                }
            }
        }

        //Other Assignments
        if(CollectionUtils.isNotEmpty(assignmentList))
        {
            OtherAssignments otherAssignments = new OtherAssignments();
            otherAssignments.setStaffPersonAssignment(assignmentList);
            xStaff.setOtherAssignments(otherAssignments);
        }

        return xStaff;
    }

    private OtherId mapOtherId(StaffIdentifier id) {
        OtherId otherId = new OtherId();
        otherId.setId(id.getStaffId());
        otherId.setType(id.getIdentificationSystemCode());

        if(otherId.isEmptyObject())
        {
            return null;
        }
        return otherId;
    }

    private Name mapName(Staff instance) {
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

    private PrimaryAssignment mapPrimaryAssignment(StaffAssignment assignment) {
        PrimaryAssignment primaryAssignment = new PrimaryAssignment();
        primaryAssignment.setJobFunction(assignment.getPositionTitle());

        if(assignment.getSchool() != null)
        {
            primaryAssignment.setSchoolRefId(assignment.getSchool().getSchoolRefId());
            if(assignment.getSchool().getLea() != null) {
                primaryAssignment.setLeaRefId(assignment.getSchool().getLea().getLeaRefId());
            }
        }

        if(primaryAssignment.isEmptyObject())
        {
            return null;
        }
        return primaryAssignment;
    }

    private StaffPersonAssignment mapOtherAssignment(StaffAssignment assignment) {
        StaffPersonAssignment staffPersonAssignment = new StaffPersonAssignment();
        staffPersonAssignment.setJobFunction(assignment.getPositionTitle());

        if(assignment.getSchool() != null)
        {
            staffPersonAssignment.setSchoolRefId(assignment.getSchool().getSchoolRefId());
            if(assignment.getSchool().getLea() != null) {
                staffPersonAssignment.setLeaRefId(assignment.getSchool().getLea().getLeaRefId());
            }
        }

        if(staffPersonAssignment.isEmptyObject())
        {
            return null;
        }
        return staffPersonAssignment;
    }

    private Email mapEmail(StaffEmail staffEmail) {
        Email email = new Email();
        email.setEmailAddress(staffEmail.getEmailAddress());
        email.setEmailType(staffEmail.getEmailTypeCode());

        if(email.isEmptyObject())
        {
            return null;
        }
        return email;
    }
}
