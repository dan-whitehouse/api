package org.ricone.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@Table(name = "staffidentifier")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StaffIdentifier implements java.io.Serializable
{
    private static final long serialVersionUID = -4307876689810647938L;
    private String staffIdentifierRefId;
    private Staff staff;
    private String identificationSystemCode;
    private String staffId;

    public StaffIdentifier()
    {
    }

    public StaffIdentifier(String staffIdentifierRefId)
    {
        this.staffIdentifierRefId = staffIdentifierRefId;
    }

    public StaffIdentifier(String staffIdentifierRefId, Staff staff, String identificationSystemCode, String staffId)
    {
        this.staffIdentifierRefId = staffIdentifierRefId;
        this.staff = staff;
        this.identificationSystemCode = identificationSystemCode;
        this.staffId = staffId;
    }

    @Id
    @Column(name = "StaffIdentifierRefId", unique = true, nullable = false, length = 64)
    public String getStaffIdentifierRefId()
    {
        return this.staffIdentifierRefId;
    }

    public void setStaffIdentifierRefId(String staffIdentifierRefId)
    {
        this.staffIdentifierRefId = staffIdentifierRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StaffRefId")
    public Staff getStaff()
    {
        return this.staff;
    }

    public void setStaff(Staff staff)
    {
        this.staff = staff;
    }

    @Column(name = "IdentificationSystemCode", length = 50)
    public String getIdentificationSystemCode()
    {
        return this.identificationSystemCode;
    }

    public void setIdentificationSystemCode(String identificationSystemCode)
    {
        this.identificationSystemCode = identificationSystemCode;
    }

    @Column(name = "StaffId", length = 50)
    public String getStaffId()
    {
        return this.staffId;
    }

    public void setStaffId(String staffId)
    {
        this.staffId = staffId;
    }

}
