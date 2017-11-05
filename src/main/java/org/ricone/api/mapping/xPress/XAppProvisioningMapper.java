package org.ricone.api.mapping.xPress;

import org.ricone.api.model.core.StaffIdentifier;
import org.ricone.api.model.core.StudentIdentifier;
import org.ricone.api.model.core.UserPassword;
import org.ricone.api.model.xpress.XAppProvisioning;
import org.ricone.api.model.xpress.XAppProvisioningInfo;
import org.ricone.api.model.xpress.XAppProvisioningResponse;
import org.ricone.api.util.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("XAppProvisioningMapper")
public class XAppProvisioningMapper {

    private final String LOGIN_ID = "LoginId";

    @Autowired
    private AES security;

    public XAppProvisioningResponse convert(List<UserPassword> instance) {
        List<XAppProvisioningInfo> list = new ArrayList<>();
        for(UserPassword userPassword : instance)
        {
            XAppProvisioningInfo xAppProvisioningInfo = map(userPassword);
            if (xAppProvisioningInfo != null) {
                list.add(xAppProvisioningInfo);
            }
        }

        XAppProvisioningResponse response = new XAppProvisioningResponse();
        XAppProvisioning xAppProvisioning = new XAppProvisioning();
        xAppProvisioning.setXAppProvisioningInfo(list);

        response.setXAppProvisioning(xAppProvisioning);
        return response;
    }

    private XAppProvisioningInfo map(UserPassword userPassword) {
        XAppProvisioningInfo xAppProvisioningInfo = new XAppProvisioningInfo();
        xAppProvisioningInfo.setRefId(userPassword.getEntityRefId());
        xAppProvisioningInfo.setType(userPassword.getEntityType().getDbValue());
        xAppProvisioningInfo.setLoginId(mapLoginId(userPassword));
        xAppProvisioningInfo.setTempPassword(mapPassword(userPassword));
        xAppProvisioningInfo.setTempPasswordExpiryDate(userPassword.getExpiryDate().toString());

        return xAppProvisioningInfo;
    }

    private String mapLoginId(UserPassword userPassword) {
        if(userPassword.getStaff() != null) {
            Optional<StaffIdentifier> identifier = userPassword.getStaff().getStaffIdentifiers().stream()
                    .filter(staffIdentifier -> staffIdentifier.getIdentificationSystemCode().equalsIgnoreCase(LOGIN_ID))
                    .findFirst();
            if(identifier.isPresent()) {
                return identifier.get().getStaffId();
            }
        }
        else if(userPassword.getStudent() != null) {
            Optional<StudentIdentifier> identifier = userPassword.getStudent().getStudentIdentifiers().stream()
                    .filter(studentIdentifier -> studentIdentifier.getIdentificationSystemCode().equalsIgnoreCase(LOGIN_ID)).findFirst();
            if(identifier.isPresent()) {
                return identifier.get().getStudentId();
            }
        }
        return null;
    }

    private String mapPassword(UserPassword userPassword) {
        return security.decrypt(userPassword.getTempPassword(), security.getRefToKey(userPassword.getEntityRefId()));
    }
}
