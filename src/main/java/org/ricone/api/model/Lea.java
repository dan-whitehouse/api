package org.ricone.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Lea generated by hbm2java
 */
@Entity
@Table(name = "lea")
@JsonInclude(value=Include.NON_EMPTY)
public class Lea implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String learefId;
	private String leaid;
	private String leaseaid;
	private String leancesid;
	private String leaname;
	private String streetNumberAndName;
	private String line2;
	private String city;
	private String stateCode;
	private String postalCode;
	private String addressCountyName;
	private String countryCode;
	private String addressType;
	private String leavendorId;
	private Set<Leatelephone> leatelephones = new HashSet<Leatelephone>(0);

	public Lea() {
	}

	public Lea(String learefId) {
		this.learefId = learefId;
	}

	public Lea(String learefId, String leaid, String leaseaid, String leancesid, String leaname,
			String streetNumberAndName, String line2, String city, String stateCode, String postalCode,
			String addressCountyName, String countryCode, String addressType, String leavendorId,
			Set<Leatelephone> leatelephones) {
		this.learefId = learefId;
		this.leaid = leaid;
		this.leaseaid = leaseaid;
		this.leancesid = leancesid;
		this.leaname = leaname;
		this.streetNumberAndName = streetNumberAndName;
		this.line2 = line2;
		this.city = city;
		this.stateCode = stateCode;
		this.postalCode = postalCode;
		this.addressCountyName = addressCountyName;
		this.countryCode = countryCode;
		this.addressType = addressType;
		this.leavendorId = leavendorId;
		this.leatelephones = leatelephones;
	}

	@Id

	@Column(name = "LEARefId", unique = true, nullable = false, length = 64)
	public String getLearefId() {
		return this.learefId;
	}

	public void setLearefId(String learefId) {
		this.learefId = learefId;
	}

	@Column(name = "LEAId", length = 30)
	public String getLeaid() {
		return this.leaid;
	}

	public void setLeaid(String leaid) {
		this.leaid = leaid;
	}

	@Column(name = "LEASEAId", length = 30)
	public String getLeaseaid() {
		return this.leaseaid;
	}

	public void setLeaseaid(String leaseaid) {
		this.leaseaid = leaseaid;
	}

	@Column(name = "LEANCESId", length = 30)
	public String getLeancesid() {
		return this.leancesid;
	}

	public void setLeancesid(String leancesid) {
		this.leancesid = leancesid;
	}

	@Column(name = "LEAName", length = 60)
	public String getLeaname() {
		return this.leaname;
	}

	public void setLeaname(String leaname) {
		this.leaname = leaname;
	}

	@Column(name = "StreetNumberAndName", length = 40)
	public String getStreetNumberAndName() {
		return this.streetNumberAndName;
	}

	public void setStreetNumberAndName(String streetNumberAndName) {
		this.streetNumberAndName = streetNumberAndName;
	}

	@Column(name = "Line2", length = 40)
	public String getLine2() {
		return this.line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
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

	@Column(name = "AddressType", length = 32)
	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Column(name = "LEAVendorId", length = 30)
	public String getLeavendorId() {
		return this.leavendorId;
	}

	public void setLeavendorId(String leavendorId) {
		this.leavendorId = leavendorId;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "lea")
	public Set<Leatelephone> getLeatelephones() {
		return this.leatelephones;
	}

	public void setLeatelephones(Set<Leatelephone> leatelephones) {
		this.leatelephones = leatelephones;
	}

}
