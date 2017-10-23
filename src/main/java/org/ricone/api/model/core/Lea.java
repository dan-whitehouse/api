package org.ricone.api.model.core;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "lea")
@JsonInclude(value=Include.NON_EMPTY)
@Cacheable @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lea")
	public Set<School> getSchools()
	{
		return this.schools;
	}
	public void setSchools(Set<School> schools)
	{
		this.schools = schools;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lea")
	public Set<LeaTelephone> getLeaTelephones()
	{
		return this.leaTelephones;
	}
	public void setLeaTelephones(Set<LeaTelephone> leatelephones)
	{
		this.leaTelephones = leatelephones;
	}
}
