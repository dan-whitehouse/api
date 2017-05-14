package org.ricone.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@Table(name = "staffassignment")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class StaffAssignment implements java.io.Serializable
{
    private static final long serialVersionUID = -3255881408462782727L;
    private String staffAssignmentRefId;
    private Staff staff;
    private School school;
    private Boolean primaryAssignment;
    private String positionTitle;

    public StaffAssignment()
    {
    }

    public StaffAssignment(String staffAssignmentRefId, Staff staff, School school)
    {
        this.staffAssignmentRefId = staffAssignmentRefId;
        this.staff = staff;
        this.school = school;
    }

    public StaffAssignment(String staffAssignmentRefId, Staff staff, School school, Boolean primaryAssignment, String positionTitle)
    {
        this.staffAssignmentRefId = staffAssignmentRefId;
        this.staff = staff;
        this.school = school;
        this.primaryAssignment = primaryAssignment;
        this.positionTitle = positionTitle;
    }

    @Id
    @Column(name = "StaffAssignmentRefId", unique = true, nullable = false, length = 64)
    public String getStaffAssignmentRefId()
    {
        return this.staffAssignmentRefId;
    }

    public void setStaffAssignmentRefId(String staffAssignmentRefId)
    {
        this.staffAssignmentRefId = staffAssignmentRefId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SchoolRefId", nullable = false)
    public School getSchool()
    {
        return this.school;
    }

    public void setSchool(School school)
    {
        this.school = school;
    }

    @Column(name = "PrimaryAssignment")
    public Boolean getPrimaryAssignment()
    {
        return this.primaryAssignment;
    }

    public void setPrimaryAssignment(Boolean primaryAssignment)
    {
        this.primaryAssignment = primaryAssignment;
    }

    @Column(name = "PositionTitle", length = 45)
    public String getPositionTitle()
    {
        return this.positionTitle;
    }

    public void setPositionTitle(String positionTitle)
    {
        this.positionTitle = positionTitle;
    }
}