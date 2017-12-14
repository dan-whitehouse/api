package org.ricone.api.xPress.request.xLea;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.LeaTelephone;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xPress.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XLeaMapper")
public class XLeaMapper {

    public XLeaMapper() {
    }

    public XLeasResponse convert(List<LeaWrapper> instance)
    {
        List<XLea> list = new ArrayList<>();
        for(LeaWrapper wrapper : instance)
        {
            XLea xLea = map(wrapper.getLea(), wrapper.getDistrictId());
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

    public XLeaResponse convert(LeaWrapper wrapper)
    {
        XLeaResponse response = new XLeaResponse();
        response.setXLea(map(wrapper.getLea(), wrapper.getDistrictId()));
        return response;
    }

    public XLea map(Lea instance, String districtId)
    {
        XLea xLea = new XLea();
        xLea.setDistrictId(districtId); //Required by Filtering
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
