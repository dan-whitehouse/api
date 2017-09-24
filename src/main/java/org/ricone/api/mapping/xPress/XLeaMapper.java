package org.ricone.api.mapping.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.LeaTelephone;
import org.ricone.api.model.core.School;
import org.ricone.api.model.xpress.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("XLeaMapper")
public class XLeaMapper {

    public XLeaMapper() {
    }

    public XLeasResponse convert(List<Lea> instance)
    {
        List<XLea> list = new ArrayList<>();
        for(Lea lea : instance)
        {
            XLea xLea = map(lea);
            if (xLea != null) {
                list.add(xLea);
            }
        }

        XLeasResponse response = new XLeasResponse();
        XLeas xLeas = new XLeas();
        xLeas.setXLea(list);

        response.setXLeas(xLeas);
        return response;
    }

    public XLeaResponse convert(Lea instance)
    {
        XLeaResponse response = new XLeaResponse();
        response.setXLea(map(instance));
        return response;
    }

    public XLea map(Lea instance)
    {
        XLea xLea = new XLea();
        xLea.setRefId(instance.getLeaRefId());
        xLea.setLeaName(instance.getLeaName());
        xLea.setLocalId(instance.getLeaId());
        xLea.setStateProvinceId(instance.getLeaSeaId());
        xLea.setNcesId(instance.getLeaNcesId());

        //Address
        Address address = mapAddress(instance);
        if (address != null) {
            xLea.setAddress(address);
        }

        //PhoneNumber - Primary
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for(LeaTelephone telephone : instance.getLeaTelephones())
        {
            PhoneNumber phone = mapPhone(telephone);
            if(phone != null)
            {
                if(telephone.getPrimaryTelephoneNumberIndicator()) {
                    xLea.setPhoneNumber(phone);
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
            xLea.setOtherPhoneNumbers(otherPhoneNumbers);
        }

        return xLea;
    }

    private Address mapAddress(Lea lea)
    {
        Address address = new Address();
        address.setAddressType(lea.getAddressType());
        address.setLine1(lea.getStreetNumberAndName());
        address.setLine2(lea.getLine2());
        address.setPostalCode(lea.getPostalCode());
        address.setCity(lea.getCity());
        address.setCountryCode(lea.getCountryCode());
        address.setStateProvince(lea.getStateCode());

        if(address.isEmptyObject())
        {
            return null;
        }
        return address;
    }

    private PhoneNumber mapPhone(LeaTelephone telephone)
    {
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
}
