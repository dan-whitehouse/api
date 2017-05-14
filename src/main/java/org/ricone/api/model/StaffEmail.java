package org.ricone.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@Table(name = "staffemail")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StaffEmail implements java.io.Serializable
{
    private static final long serialVersionUID = -1105974485352114510L;
    private String staffEmailRefId;
    private Staff staff;
    private String emailAddress;
    private String emailTypeCode;
    private Boolean primaryEmailAddressIndicator;

    public StaffEmail()
    {
    }

    public StaffEmail(String staffEmailRefId, Staff staff)
    {
        this.staffEmailRefId = staffEmailRefId;
        this.staff = staff;
    }

    public StaffEmail(String staffEmailRefId, Staff staff, String emailAddress, String emailTypeCode, Boolean primaryEmailAddressIndicator)
    {
        this.staffEmailRefId = staffEmailRefId;
        this.staff = staff;
        this.emailAddress = emailAddress;
        this.emailTypeCode = emailTypeCode;
        this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
    }

    @Id
    @Column(name = "StaffEmailRefId", unique = true, nullable = false, length = 64)
    public String getStaffEmailRefId()
    {
        return this.staffEmailRefId;
    }

    public void setStaffEmailRefId(String staffEmailRefId)
    {
        this.staffEmailRefId = staffEmailRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StaffRefId", nullable = false)
    public Staff getStaff()
    {
        return this.staff;
    }

    public void setStaff(Staff staff)
    {
        this.staff = staff;
    }

    @Column(name = "EmailAddress", length = 128)
    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    @Column(name = "EmailTypeCode", length = 50)
    public String getEmailTypeCode()
    {
        return this.emailTypeCode;
    }

    public void setEmailTypeCode(String emailTypeCode)
    {
        this.emailTypeCode = emailTypeCode;
    }

    @Column(name = "PrimaryEmailAddressIndicator")
    public Boolean getPrimaryEmailAddressIndicator()
    {
        return this.primaryEmailAddressIndicator;
    }

    public void setPrimaryEmailAddressIndicator(Boolean primaryEmailAddressIndicator)
    {
        this.primaryEmailAddressIndicator = primaryEmailAddressIndicator;
    }
}