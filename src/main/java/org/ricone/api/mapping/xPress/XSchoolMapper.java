package org.ricone.api.mapping.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.model.core.*;
import org.ricone.api.model.xpress.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("XSchoolMapper")
public class XSchoolMapper {

    private final String LOCAL_ID = "LEA";
    private final String STATE_ID = "SEA";

    public XSchoolMapper() {
    }

    public XSchoolsResponse convert(List<School> instance)
    {
        List<XSchool> list = new ArrayList<>();
        for(School school : instance)
        {
            XSchool xSchool = map(school);
            if (xSchool != null) {
                list.add(xSchool);
            }
        }

        XSchoolsResponse response = new XSchoolsResponse();
        XSchools xSchools = new XSchools();
        xSchools.setXSchool(list);

        response.setXSchools(xSchools);
        return response;
    }

    public XSchoolResponse convert(School instance)
    {
        XSchoolResponse response = new XSchoolResponse();
        XSchool xSchool = map(instance);
        if (xSchool != null) {
            response.setXSchool(xSchool);
        }
        return response;
    }


    public XSchool map(School instance)
    {
        XSchool xSchool = new XSchool();
        xSchool.setRefId(instance.getSchoolRefId());
        xSchool.setSchoolName(instance.getSchoolName());
        xSchool.setLeaRefId(instance.getLea().getLeaRefId());

        //Address
        Address address = mapAddress(instance);
        if(address != null) {
            xSchool.setAddress(address);
        }

        //PhoneNumber - Primary
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for(SchoolTelephone telephone : instance.getSchoolTelephones())
        {
            PhoneNumber phone = mapPhone(telephone);
            if(phone != null)
            {
                if(telephone.getPrimaryTelephoneNumberIndicator()) {
                    xSchool.setPhoneNumber(phone);
                }
                else {
                    phoneNumbers.add(phone);
                }
            }
        }

        //PhoneNumbers - Other
        if(CollectionUtils.isNotEmpty(phoneNumbers))
        {
            OtherPhoneNumbers otherPhoneNumbers = new OtherPhoneNumbers();
            otherPhoneNumbers.setPhoneNumber(phoneNumbers);
            xSchool.setOtherPhoneNumbers(otherPhoneNumbers);
        }

        //Grade Levels
        GradeLevels gradeLevels = mapGrades(instance.getSchoolGrades());
        if(gradeLevels != null){
            xSchool.setGradeLevels(gradeLevels);
        }

        //Identifiers
        List<OtherId> otherIdList = new ArrayList<>();
        for(SchoolIdentifier id : instance.getSchoolIdentifiers())
        {
            if(LOCAL_ID.equals(id.getIdentificationSystemCode())) {
                xSchool.setLocalId(id.getSchoolId());
            }
            else if(STATE_ID.equals(id.getIdentificationSystemCode())) {
                xSchool.setStateProvinceId(id.getSchoolId());
            }
            else
            {
                OtherId otherId = mapOtherId(id);
                if(otherId != null){
                    otherIdList.add(otherId);
                }
            }
        }

        //Other Identifiers
        if(CollectionUtils.isNotEmpty(otherIdList))
        {
            OtherIds otherIds = new OtherIds();
            otherIds.setOtherId(otherIdList);
            xSchool.setOtherIds(otherIds);
        }

        return xSchool;
    }

    private OtherId mapOtherId(SchoolIdentifier id) {
        OtherId otherId = new OtherId();
        otherId.setId(id.getSchoolId());
        otherId.setType(id.getIdentificationSystemCode());

        if(otherId.isEmptyObject())
        {
            return null;
        }
        return otherId;
    }

    private Address mapAddress(School school) {
        Address address = new Address();
        address.setAddressType(school.getAddressType());
        address.setLine1(school.getStreetNumberAndName());
        address.setLine2(school.getLine2());
        address.setPostalCode(school.getPostalCode());
        address.setCity(school.getCity());
        address.setCountryCode(school.getCountryCode());
        address.setStateProvince(school.getStateCode());

        if(address.isEmptyObject())
        {
            return null;
        }
        return address;
    }

    private PhoneNumber mapPhone(SchoolTelephone telephone) {
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

    private GradeLevels mapGrades(Set<SchoolGrade> grades) {
        GradeLevels gradeLevels = new GradeLevels();
        for(SchoolGrade grade : grades)
        {
            gradeLevels.getGradeLevel().add(grade.getGradeLevelCode());
        }

        if(gradeLevels.isEmptyObject())
        {
            return null;
        }
        return gradeLevels;
    }
}
