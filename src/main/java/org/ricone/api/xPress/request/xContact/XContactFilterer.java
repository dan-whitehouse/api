package org.ricone.api.xPress.request.xContact;

import org.ricone.api.xPress.model.*;
import org.ricone.authentication.MetaData;
import org.ricone.config.model.XContactFilter;
import org.springframework.stereotype.Component;

@Component("XContactFilterer")
public class XContactFilterer {
    public XContactFilterer() {
    }

    public XContacts apply(XContacts instance, MetaData metaData) {
        for (XContact i : instance.getXContact()) {
            i = filter(i, null);
        }
        return instance;
    }

    public XContact apply(XContact instance, MetaData metaData) {
        instance = filter(instance, null);
        return instance;
    }

    public XContact filter(XContact instance, XContactFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }

        //Name
        if(instance.getName() != null) {
            if(!filter.getNamefamilyName()) {
                instance.getName().setFamilyName(null);
            }
            if(!filter.getNamegivenName()) {
                instance.getName().setGivenName(null);
            }
            if(!filter.getNamemiddleName()) {
                instance.getName().setMiddleName(null);
            }
            if(!filter.getNameprefix()) {
                instance.getName().setPrefix(null);
            }
            if(!filter.getNamesuffix()) {
                instance.getName().setSuffix(null);
            }
            if(!filter.getNametype()) {
                instance.getName().setType(null);
            }
        }

        //Other Names
        for (Name i : instance.getOtherNames().getName()) {
            if(!filter.getOtherNamesnamefamilyName()) {
                i.setFamilyName(null);
            }
            if(!filter.getOtherNamesnamegivenName()) {
                i.setGivenName(null);
            }
            if(!filter.getOtherNamesnamemiddleName()) {
                i.setMiddleName(null);
            }
            if(!filter.getOtherNamesnameprefix()) {
                i.setPrefix(null);
            }
            if(!filter.getOtherNamesnamesuffix()) {
                i.setSuffix(null);
            }
            if(!filter.getOtherNamesnametype()) {
                i.setType(null);
            }
        }

        //Email
        if(instance.getEmail() != null) {
            if(!filter.getEmailemailType()) {
                instance.getEmail().setEmailType(null);
            }
            if(!filter.getEmailemailAddress()) {
                instance.getEmail().setEmailAddress(null);
            }
        }

        //Other Email
        for (Email i : instance.getOtherEmails().getEmail()) {
            if(!filter.getOtherEmailsemailemailType()) {
                i.setEmailType(null);
            }
            if(!filter.getOtherEmailsemailemailAddress()) {
                i.setEmailAddress(null);
            }
        }

        //Identifiers
        if(!filter.getLocalId()) {
            instance.setLocalId(null);
        }


        //Other Identifiers
        for (OtherId i : instance.getOtherIds().getOtherId()) {
            if(!filter.getOtherIdsotherIdid()) {
                i.setId(null);
            }
            if(!filter.getOtherIdsotherIdtype()) {
                i.setType(null);
            }
        }

        //Address
        if(instance.getAddress() != null) {
            if(!filter.getAddressaddressType()) {
                instance.getAddress().setAddressType(null);
            }
            if(!filter.getAddresscity()) {
                instance.getAddress().setCity(null);
            }
            if(!filter.getAddresscountryCode()) {
                instance.getAddress().setCountryCode(null);
            }
            if(!filter.getAddressline1()) {
                instance.getAddress().setLine1(null);
            }
            if(!filter.getAddressline2()) {
                instance.getAddress().setLine2(null);
            }
            if(!filter.getAddresspostalCode()) {
                instance.getAddress().setPostalCode(null);
            }
            if(!filter.getAddressstateProvince()) {
                instance.getAddress().setStateProvince(null);
            }
        }


        //Primary Phone Number
        if(instance.getPhoneNumber() != null) {
            if(!filter.getPhoneNumbernumber()) {
                instance.getPhoneNumber().setNumber(null);
            }
            if(!filter.getPhoneNumberphoneNumberType()) {
                instance.getPhoneNumber().setPhoneNumberType(null);
            }
            if(!filter.getPhoneNumberprimaryIndicator()) {
                instance.getPhoneNumber().setPrimaryIndicator(null);
            }
        }

        //Other Phone Numbers
        for (PhoneNumber i : instance.getOtherPhoneNumbers().getPhoneNumber()) {
            if(!filter.getOtherPhoneNumbersphoneNumbernumber()) {
                i.setNumber(null);
            }
            if(!filter.getOtherPhoneNumbersphoneNumberphoneNumberType()) {
                i.setPhoneNumberType(null);
            }
            if(!filter.getOtherPhoneNumbersphoneNumberprimaryIndicator()) {
                i.setPrimaryIndicator(null);
            }
        }


        //Academic Summary
        for (Relationship i : instance.getRelationships().getRelationship()) {
            if(!filter.getRelationshipsrelationshipcommunicationsIndicator()) {
                i.setCommunicationsIndicator(null);
            }
            if(!filter.getRelationshipsrelationshipcontactSequence()) {
                i.setContactSequence(null);
            }
            if(!filter.getRelationshipsrelationshipcustodialIndicator()) {
                i.setCustodialIndicator(null);
            }
            if(!filter.getRelationshipsrelationshipemergencyContactIndicator()) {
                i.setEmergencyContactIndicator(null);
            }
            if(!filter.getRelationshipsrelationshipfinancialResponsibilityIndicator()) {
                i.setFinancialResponsibilityIndicator(null);
            }
            if(!filter.getRelationshipsrelationshiplivesWith()) {
                i.setLivesWith(null);
            }
            if(!filter.getRelationshipsrelationshipprimaryContactIndicator()) {
                i.setPrimaryContactIndicator(null);
            }
            if(!filter.getRelationshipsrelationshiprelationshipCode()) {
                i.setRelationshipCode(null);
            }
            if(!filter.getRelationshipsrelationshiprestrictions()) {
                i.setRestrictions(null);
            }
            if(!filter.getRelationshipsrelationshipstudentRefId()) {
                i.setStudentRefId(null);
            }
        }
        return instance;
    }
}