package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "studentcontactaddress")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StudentContactAddress implements java.io.Serializable {
    private static final long serialVersionUID = -4100803613028097486L;
    private String studentContactAddressRefId;
    private StudentContact studentContact;
    private String addressTypeCode;
    private String streetNumberAndName;
    private String apartmentRoomOrSuiteNumber;
    private String city;
    private String stateCode;
    private String postalCode;
    private String addressCountyName;
    private String countryCode;

    public StudentContactAddress() {
    }

    public StudentContactAddress(String studentContactAddressRefId, StudentContact studentContact) {
        this.studentContactAddressRefId = studentContactAddressRefId;
        this.studentContact = studentContact;
    }

    public StudentContactAddress(String studentContactAddressRefId, StudentContact studentContact, String addressTypeCode, String streetNumberAndName, String apartmentRoomOrSuiteNumber, String city, String stateCode, String postalCode, String addressCountyName, String countryCode) {
        this.studentContactAddressRefId = studentContactAddressRefId;
        this.studentContact = studentContact;
        this.addressTypeCode = addressTypeCode;
        this.streetNumberAndName = streetNumberAndName;
        this.apartmentRoomOrSuiteNumber = apartmentRoomOrSuiteNumber;
        this.city = city;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
        this.addressCountyName = addressCountyName;
        this.countryCode = countryCode;
    }

    @Id
    @Column(name = "StudentContactAddressRefId", unique = true, nullable = false, length = 64)
    public String getStudentContactAddressRefId() {
        return this.studentContactAddressRefId;
    }

    public void setStudentContactAddressRefId(String studentContactAddressRefId) {
        this.studentContactAddressRefId = studentContactAddressRefId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StudentContactRefId", nullable = false)
    public StudentContact getStudentContact() {
        return this.studentContact;
    }

    public void setStudentContact(StudentContact studentContact) {
        this.studentContact = studentContact;
    }

    @Column(name = "AddressTypeCode", length = 50)
    public String getAddressTypeCode() {
        return this.addressTypeCode;
    }

    public void setAddressTypeCode(String addressTypeCode) {
        this.addressTypeCode = addressTypeCode;
    }

    @Column(name = "StreetNumberAndName", length = 40)
    public String getStreetNumberAndName() {
        return this.streetNumberAndName;
    }

    public void setStreetNumberAndName(String streetNumberAndName) {
        this.streetNumberAndName = streetNumberAndName;
    }

    @Column(name = "ApartmentRoomOrSuiteNumber", length = 30)
    public String getApartmentRoomOrSuiteNumber() {
        return this.apartmentRoomOrSuiteNumber;
    }

    public void setApartmentRoomOrSuiteNumber(String apartmentRoomOrSuiteNumber) {
        this.apartmentRoomOrSuiteNumber = apartmentRoomOrSuiteNumber;
    }

    @Column(name = "City", length = 30)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "StateCode", length = 50)
    public String getStateCode() {
        return this.stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Column(name = "PostalCode", length = 50)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "AddressCountyName", length = 30)
    public String getAddressCountyName() {
        return this.addressCountyName;
    }

    public void setAddressCountyName(String addressCountyName) {
        this.addressCountyName = addressCountyName;
    }

    @Column(name = "CountryCode", length = 50)
    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}