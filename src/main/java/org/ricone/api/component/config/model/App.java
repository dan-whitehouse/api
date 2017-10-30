package org.ricone.api.component.config.model;

import org.ricone.api.model.core.Lea;

import java.util.List;
import java.util.stream.Collectors;

public class App
{
	private String id;
	private String severityLevelDataAPI;
	private String providerSecret;
	private String profile_id;

	private List<District> districts;
	private List<Lea> leas;

	public App()
	{
		super();
	}

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}

	public String getSeverityLevelDataAPI()
	{
		return severityLevelDataAPI;
	}
	public void setSeverityLevelDataAPI(String severityLevelDataAPI)
	{
		this.severityLevelDataAPI = severityLevelDataAPI;
	}

	public String getProviderSecret()
	{
		return providerSecret;
	}
	public void setProviderSecret(String providerSecret)
	{
		this.providerSecret = providerSecret;
	}

	public String getProfile_id()
	{
		return profile_id;
	}
	public void setProfile_id(String profile_id) { this.profile_id = profile_id; }

	public List<District> getDistricts() { return districts; }
	public void setDistricts(List<District> districts) { this.districts = districts; }

	public List<Lea> getLeas() { return leas; }
	public void setLeas(List<Lea> leas) { this.leas = leas; }

	public List<String> getDistrictLocalIds()
	{
		return districts.stream().map(District::getId).collect(Collectors.toList());
	}



	@Override
	public String toString() {
		return "App{" +
				"id='" + id + '\'' +
				", severityLevelDataAPI='" + severityLevelDataAPI + '\'' +
				", providerSecret='" + providerSecret + '\'' +
				", profile_id='" + profile_id + '\'' +
				", districts=" + districts +
				'}';
	}
}
