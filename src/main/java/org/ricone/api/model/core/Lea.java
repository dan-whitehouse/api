package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lea")
@JsonInclude(value=Include.NON_EMPTY)
@Cacheable @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Lea implements java.io.Serializable
{
	private static final long serialVersionUID = -2620417938122940193L;
	private String leaRefId;
	private String leaId;
	private String leaSeaId;
	private String leaNcesId;
	private String leaName;
	private String streetNumberAndName;
	private String city;
	private String stateCode;
	private String postalCode;
	private String addressCountyName;
	private String leaVendorId;
	private String addressType;
	private String line2;
	private String countryCode;
	private Set<School> schools = new HashSet<>(0);
	private Set<LeaTelephone> leaTelephones = new HashSet<>(0);

	public Lea()
	{
	}

	public Lea(String learefId)
	{
		this.leaRefId = learefId;
	}

	public Lea(String leaRefId, String leaId, String leaSeaId, String leaNcesId, String leaName, String streetNumberAndName, String city, String stateCode, String postalCode, String addressCountyName, String leaVendorId, String addressType, String line2, String countryCode, Set<School> schools, Set<LeaTelephone> leaTelephones)
	{
		this.leaRefId = leaRefId;
		this.leaId = leaId;
		this.leaSeaId = leaSeaId;
		this.leaNcesId = leaNcesId;
		this.leaName = leaName;
		this.streetNumberAndName = streetNumberAndName;
		this.city = city;
		this.stateCode = stateCode;
		this.postalCode = postalCode;
		this.addressCountyName = addressCountyName;
		this.leaVendorId = leaVendorId;
		this.addressType = addressType;
		this.line2 = line2;
		this.countryCode = countryCode;
		this.schools = schools;
		this.leaTelephones = leaTelephones;
	}

	@Id
	@Column(name = "LEARefId", unique = true, nullable = false, length = 64)
	public String getLeaRefId()
	{
		return this.leaRefId;
	}
	public void setLeaRefId(String learefId)
	{
		this.leaRefId = learefId;
	}

	@Column(name = "LEAId", length = 30)
	public String getLeaId()
	{
		return this.leaId;
	}
	public void setLeaId(String leaid)
	{
		this.leaId = leaid;
	}

	@Column(name = "LEASEAId", length = 30)
	public String getLeaSeaId()
	{
		return this.leaSeaId;
	}
	public void setLeaSeaId(String leaseaid)
	{
		this.leaSeaId = leaseaid;
	}

	@Column(name = "LEANCESId", length = 30)
	public String getLeaNcesId()
	{
		return this.leaNcesId;
	}
	public void setLeaNcesId(String leancesid)
	{
		this.leaNcesId = leancesid;
	}

	@Column(name = "LEAName", length = 60)
	public String getLeaName()
	{
		return this.leaName;
	}
	public void setLeaName(String leaname)
	{
		this.leaName = leaname;
	}

	@Column(name = "StreetNumberAndName", length = 40)
	public String getStreetNumberAndName()
	{
		return this.streetNumberAndName;
	}
	public void setStreetNumberAndName(String streetNumberAndName)
	{
		this.streetNumberAndName = streetNumberAndName;
	}

	@Column(name = "City", length = 30)
	public String getCity()
	{
		return this.city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}

	@Column(name = "StateCode", length = 50)
	public String getStateCode()
	{
		return this.stateCode;
	}
	public void setStateCode(String stateCode)
	{
		this.stateCode = stateCode;
	}

	@Column(name = "PostalCode", length = 50)
	public String getPostalCode()
	{
		return this.postalCode;
	}
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	@Column(name = "AddressCountyName", length = 30)
	public String getAddressCountyName()
	{
		return this.addressCountyName;
	}
	public void setAddressCountyName(String addressCountyName)
	{
		this.addressCountyName = addressCountyName;
	}

	@Column(name = "LEAVendorId", length = 30)
	public String getLeaVendorId()
	{
		return this.leaVendorId;
	}
	public void setLeaVendorId(String leavendorId)
	{
		this.leaVendorId = leavendorId;
	}

	@Column(name = "AddressType", length = 32)
	public String getAddressType()
	{
		return addressType;
	}
	public void setAddressType(String addressType)
	{
		this.addressType = addressType;
	}

	@Column(name = "Line2", length = 40)
	public String getLine2()
	{
		return line2;
	}
	public void setLine2(String line2)
	{
		this.line2 = line2;
	}

	@Column(name = "CountryCode", length = 50)
	public String getCountryCode()
	{
		return countryCode;
	}
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lea") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	public Set<School> getSchools()
	{
		return this.schools;
	}
	public void setSchools(Set<School> schools)
	{
		this.schools = schools;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lea") @Fetch(FetchMode.SELECT) @BatchSize(size = 20)
	public Set<LeaTelephone> getLeaTelephones()
	{
		return this.leaTelephones;
	}
	public void setLeaTelephones(Set<LeaTelephone> leatelephones)
	{
		this.leaTelephones = leatelephones;
	}

	@Override
	public String toString() {
		return "Lea{" +
				"leaRefId='" + leaRefId + '\'' +
				", leaId='" + leaId + '\'' +
				", leaSeaId='" + leaSeaId + '\'' +
				", leaNcesId='" + leaNcesId + '\'' +
				", leaName='" + leaName + '\'' +
				", streetNumberAndName='" + streetNumberAndName + '\'' +
				", city='" + city + '\'' +
				", stateCode='" + stateCode + '\'' +
				", postalCode='" + postalCode + '\'' +
				", addressCountyName='" + addressCountyName + '\'' +
				", leaVendorId='" + leaVendorId + '\'' +
				", addressType='" + addressType + '\'' +
				", line2='" + line2 + '\'' +
				", countryCode='" + countryCode + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Lea)) return false;

		Lea lea = (Lea) o;

		if (getLeaRefId() != null ? !getLeaRefId().equals(lea.getLeaRefId()) : lea.getLeaRefId() != null) return false;
		if (getLeaId() != null ? !getLeaId().equals(lea.getLeaId()) : lea.getLeaId() != null) return false;
		if (getLeaSeaId() != null ? !getLeaSeaId().equals(lea.getLeaSeaId()) : lea.getLeaSeaId() != null) return false;
		if (getLeaNcesId() != null ? !getLeaNcesId().equals(lea.getLeaNcesId()) : lea.getLeaNcesId() != null)
			return false;
		if (getLeaName() != null ? !getLeaName().equals(lea.getLeaName()) : lea.getLeaName() != null) return false;
		if (getStreetNumberAndName() != null ? !getStreetNumberAndName().equals(lea.getStreetNumberAndName()) : lea.getStreetNumberAndName() != null)
			return false;
		if (getCity() != null ? !getCity().equals(lea.getCity()) : lea.getCity() != null) return false;
		if (getStateCode() != null ? !getStateCode().equals(lea.getStateCode()) : lea.getStateCode() != null)
			return false;
		if (getPostalCode() != null ? !getPostalCode().equals(lea.getPostalCode()) : lea.getPostalCode() != null)
			return false;
		if (getAddressCountyName() != null ? !getAddressCountyName().equals(lea.getAddressCountyName()) : lea.getAddressCountyName() != null)
			return false;
		if (getLeaVendorId() != null ? !getLeaVendorId().equals(lea.getLeaVendorId()) : lea.getLeaVendorId() != null)
			return false;
		if (getAddressType() != null ? !getAddressType().equals(lea.getAddressType()) : lea.getAddressType() != null)
			return false;
		if (getLine2() != null ? !getLine2().equals(lea.getLine2()) : lea.getLine2() != null) return false;
		if (getCountryCode() != null ? !getCountryCode().equals(lea.getCountryCode()) : lea.getCountryCode() != null)
			return false;
		if (getSchools() != null ? !getSchools().equals(lea.getSchools()) : lea.getSchools() != null) return false;
		return getLeaTelephones() != null ? getLeaTelephones().equals(lea.getLeaTelephones()) : lea.getLeaTelephones() == null;
	}

	@Override
	public int hashCode() {
		int result = getLeaRefId() != null ? getLeaRefId().hashCode() : 0;
		result = 31 * result + (getLeaId() != null ? getLeaId().hashCode() : 0);
		result = 31 * result + (getLeaSeaId() != null ? getLeaSeaId().hashCode() : 0);
		result = 31 * result + (getLeaNcesId() != null ? getLeaNcesId().hashCode() : 0);
		result = 31 * result + (getLeaName() != null ? getLeaName().hashCode() : 0);
		result = 31 * result + (getStreetNumberAndName() != null ? getStreetNumberAndName().hashCode() : 0);
		result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
		result = 31 * result + (getStateCode() != null ? getStateCode().hashCode() : 0);
		result = 31 * result + (getPostalCode() != null ? getPostalCode().hashCode() : 0);
		result = 31 * result + (getAddressCountyName() != null ? getAddressCountyName().hashCode() : 0);
		result = 31 * result + (getLeaVendorId() != null ? getLeaVendorId().hashCode() : 0);
		result = 31 * result + (getAddressType() != null ? getAddressType().hashCode() : 0);
		result = 31 * result + (getLine2() != null ? getLine2().hashCode() : 0);
		result = 31 * result + (getCountryCode() != null ? getCountryCode().hashCode() : 0);
		result = 31 * result + (getSchools() != null ? getSchools().hashCode() : 0);
		result = 31 * result + (getLeaTelephones() != null ? getLeaTelephones().hashCode() : 0);
		return result;
	}
}
