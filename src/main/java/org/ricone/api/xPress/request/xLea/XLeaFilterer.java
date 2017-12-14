package org.ricone.api.xPress.request.xLea;

import org.ricone.api.xPress.model.PhoneNumber;
import org.ricone.api.xPress.model.XLea;
import org.ricone.api.xPress.model.XLeaResponse;
import org.ricone.api.xPress.model.XLeasResponse;
import org.ricone.authentication.MetaData;
import org.ricone.config.model.XLeaFilter;
import org.springframework.stereotype.Component;

@Component("XLeaFilterer")
public class XLeaFilterer
{
    public XLeaFilterer() {
    }

    public XLeasResponse apply(XLeasResponse instance, MetaData metaData) {

    	//TODO - Remove Filter, this is a test
		XLeaFilter filter = new XLeaFilter();
		filter.setRefId(true);

        for(XLea i : instance.getXLeas().getXLea()) {
            i = filter(i, filter);
        }
        return instance;
    }
    
    public XLeaResponse apply(XLeaResponse instance, MetaData metaData) {
    	instance.setXLea(filter(instance.getXLea(), null));
        return instance;
    }
    
    public XLea filter(XLea instance, XLeaFilter filter) {
    	try {
			if (!filter.getRefId()) {
				instance.setRefId(null);
			}
			if (!filter.getLeaName()) {
				instance.setLeaName(null);
			}
			if (!filter.getLocalId()) {
				instance.setLocalId(null);
			}
			if (!filter.getStateProvinceId()) {
				instance.setStateProvinceId(null);
			}
			if (!filter.getNcesId()) {
				instance.setNcesId(null);
			}

			//Address
			if (instance.getAddress() != null) {
				if (!filter.getAddressaddressType()) {
					instance.getAddress().setAddressType(null);
				}
				if (!filter.getAddresscity()) {
					instance.getAddress().setCity(null);
				}
				if (!filter.getAddresscountryCode()) {
					instance.getAddress().setCountryCode(null);
				}
				if (!filter.getAddressline1()) {
					instance.getAddress().setLine1(null);
				}
				if (!filter.getAddressline2()) {
					instance.getAddress().setLine2(null);
				}
				if (!filter.getAddresspostalCode()) {
					instance.getAddress().setPostalCode(null);
				}
				if (!filter.getAddressstateProvince()) {
					instance.getAddress().setStateProvince(null);
				}
			}

			//Primary Phone Number
			if (instance.getPhoneNumber() != null) {
				if (!filter.getPhoneNumbernumber()) {
					instance.getPhoneNumber().setNumber(null);
				}
				if (!filter.getPhoneNumberphoneNumberType()) {
					instance.getPhoneNumber().setPhoneNumberType(null);
				}
				if (!filter.getPhoneNumberprimaryIndicator()) {
					instance.getPhoneNumber().setPrimaryIndicator(null);
				}
			}

			//Other Phone Numbers
			if(instance.getOtherPhoneNumbers() != null) {
				for (PhoneNumber i : instance.getOtherPhoneNumbers().getPhoneNumber()) {
					if (!filter.getOtherPhoneNumbersphoneNumbernumber()) {
						i.setNumber(null);
					}
					if (!filter.getOtherPhoneNumbersphoneNumberphoneNumberType()) {
						i.setPhoneNumberType(null);
					}
					if (!filter.getOtherPhoneNumbersphoneNumberprimaryIndicator()) {
						i.setPrimaryIndicator(null);
					}
				}
			}
		}
		catch (Exception e) {
    		e.printStackTrace();
		}
        return instance;
    }
}