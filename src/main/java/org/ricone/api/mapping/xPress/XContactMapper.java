package org.ricone.api.mapping.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.ricone.api.model.core.*;
import org.ricone.api.model.xpress.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("XContactMapper")
public class XContactMapper {

    private final String LOCAL_ID = "LEA";
    private final String PRIMARY_ADDRESS_TYPE = "Mailing";

    public XContactMapper() {
    }

    public XContactsResponse convert(List<StudentContact> instance)
    {
        List<XContact> list = new ArrayList<>();
        for(StudentContact contact : instance)
        {
            XContact xContact = map(contact);
            if (xContact != null) {
                list.add(xContact);
            }
        }

        XContactsResponse response = new XContactsResponse();
        XContacts xContacts = new XContacts();
        xContacts.setXContact(list);

        response.setXContacts(xContacts);
        return response;
    }

    public XContactResponse convert(StudentContact instance)
    {
        XContactResponse response = new XContactResponse();
        XContact xContact = map(instance);
        if (xContact != null) {
            response.setXContact(xContact);
        }
        return response;
    }

    public XContact map(StudentContact instance)
    {
        XContact xContact = new XContact();
        xContact.setRefId(instance.getStudentContactRefId());
        xContact.setEmployerType(instance.getEmployerType());
        xContact.setSex(instance.getSexCode());

        //Name
        Name name = mapName(instance);
        if(name != null) {
            xContact.setName(name);
        }

        //Other Names
        List<Name> otherNameList = new ArrayList<>();
        for(StudentContactOtherName contactOtherName : instance.getStudentContactOtherNames())
        {
            Name otherName = mapOtherName(contactOtherName);
            if(otherName != null){
                otherNameList.add(otherName);
            }
        }
        if(CollectionUtils.isNotEmpty(otherNameList))
        {
            OtherNames otherNames = new OtherNames();
            otherNames.setName(otherNameList);
            xContact.setOtherNames(otherNames);
        }

        //Email
        List<Email> emailList = new ArrayList<>();
        for(StudentContactEmail contactEmail : instance.getStudentContactEmails())
        {
            Email email = mapEmail(contactEmail);
            if(email != null) {
                if (contactEmail.getPrimaryEmailAddressIndicator()) {
                    xContact.setEmail(email);
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
            xContact.setOtherEmails(otherEmails);
        }

        //Identifiers
        List<OtherId> otherIdList = new ArrayList<>();
        for(StudentContactIdentifier id : instance.getStudentContactIdentifiers())
        {
            if(LOCAL_ID.equals(id.getIdentificationSystemCode())) {
                xContact.setLocalId(id.getStudentContactId());
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
            xContact.setOtherIds(otherIds);
        }

        //Address
        for(StudentContactAddress contactAddress : instance.getStudentContactAddresses())
        {
            if(PRIMARY_ADDRESS_TYPE.equalsIgnoreCase(contactAddress.getAddressTypeCode()))
            {
                Address address = mapAddress(contactAddress);
                if(address != null) {
                    xContact.setAddress(address);
                    break;
                }
            }
        }

        //Phone
        for(StudentContactTelephone telephone : instance.getStudentContactTelephones())
        {
            if(telephone.getPrimaryTelephoneNumberIndicator())
            {
                PhoneNumber phoneNumber = mapPhone(telephone);
                if(phoneNumber != null) {
                    xContact.setPhoneNumber(phoneNumber);
                    break;
                }
            }
        }

        //Relationships
        List<Relationship> relationshipList = new ArrayList<>();
        for(StudentContactRelationship studentContactRelationship : instance.getStudentContactRelationships())
        {
            Relationship relationship = mapRelationship(studentContactRelationship);
            if(relationship != null) {
                relationshipList.add(relationship);
            }
        }
        if(CollectionUtils.isNotEmpty(relationshipList))
        {
            Relationships relationships = new Relationships();
            relationships.setRelationship(relationshipList);
            xContact.setRelationships(relationships);
        }


        return xContact;
    }


    private Address mapAddress(StudentContactAddress contactAddress) {
        Address address = new Address();
        address.setAddressType(contactAddress.getAddressTypeCode());
        address.setLine1(contactAddress.getStreetNumberAndName());
        address.setLine2(contactAddress.getApartmentRoomOrSuiteNumber());
        address.setPostalCode(contactAddress.getPostalCode());
        address.setCity(contactAddress.getCity());
        address.setCountryCode(contactAddress.getCountryCode());
        address.setStateProvince(contactAddress.getStateCode());

        if(address.isEmptyObject())
        {
            return null;
        }
        return address;
    }

    private PhoneNumber mapPhone(StudentContactTelephone telephone) {
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

    private OtherId mapOtherId(StudentContactIdentifier id) {
        OtherId otherId = new OtherId();
        otherId.setId(id.getStudentContactId());
        otherId.setType(id.getIdentificationSystemCode());

        if(otherId.isEmptyObject())
        {
            return null;
        }
        return otherId;
    }

    private Name mapName(StudentContact instance) {
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

    private Name mapOtherName(StudentContactOtherName otherName) {
        Name name = new Name();
        name.setFamilyName(otherName.getLastName());
        name.setGivenName(otherName.getFirstName());
        name.setMiddleName(otherName.getMiddleName());
        name.setPrefix(otherName.getPrefix());
        name.setSuffix(otherName.getGenerationCode());
        name.setType(otherName.getType());

        if(name.isEmptyObject())
        {
            return null;
        }
        return name;
    }

    private Email mapEmail(StudentContactEmail contactEmail) {
        Email email = new Email();
        email.setEmailAddress(contactEmail.getEmailAddress());
        email.setEmailType(contactEmail.getEmailTypeCode());

        if(email.isEmptyObject())
        {
            return null;
        }
        return email;
    }

    private Relationship mapRelationship(StudentContactRelationship r) {
        Relationship relationship = new Relationship();
        relationship.setCommunicationsIndicator(BooleanUtils.toStringTrueFalse(r.getCommunicationsIndicator()));
        relationship.setContactSequence(Objects.toString(r.getContactPriorityNumber()));
        relationship.setCustodialIndicator(BooleanUtils.toStringTrueFalse(r.getCustodialRelationshipIndicator()));
        relationship.setEmergencyContactIndicator(BooleanUtils.toStringTrueFalse(r.getEmergencyContactIndicator()));
        relationship.setFinancialResponsibilityIndicator(BooleanUtils.toStringTrueFalse(r.getFinancialResponsibilityIndicator()));
        relationship.setLivesWith(BooleanUtils.toStringTrueFalse(r.getLivesWithIndicator()));
        relationship.setPrimaryContactIndicator(BooleanUtils.toStringTrueFalse(r.getPrimaryContactIndicator()));
        relationship.setRelationshipCode(r.getRelationshipCode());
        relationship.setRestrictions(r.getContactRestrictions());
        relationship.setStudentRefId(r.getStudent().getStudentRefId());

        if(relationship.isEmptyObject())
        {
            return null;
        }
        return relationship;
    }
}