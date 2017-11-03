package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.ricone.api.model.core.extension.userpassword.EntityType;
import org.ricone.api.model.core.extension.userpassword.EntityTypeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@IdClass(UserPasswordComposite.class)
@Table(name = "userPassword")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class UserPassword implements Serializable
{
	private static final long serialVersionUID = 8607824899883665976L;

	@Id
	private String entityRefId;
    @Id private EntityType entityType;
    @Id private String appId;

    private String tempPassword;
	private Date expiryDate;
	private Date lastRetrieved;
	private Date generationDate;

	@Column(name = "entityRefId", nullable = false)
	public String getEntityRefId() { return entityRefId; }
	public void setEntityRefId(String entityRefId) { this.entityRefId = entityRefId; }

	@Column(name = "entityType", nullable = false)
	@Convert(converter = EntityTypeConverter.class)
	public EntityType getEntityType() { return entityType; }
	public void setEntityType(EntityType entityType) { this.entityType = entityType; }

	@Column(name = "appId", nullable = false)
	public String getAppId() { return appId; }
	public void setAppId(String appId) { this.appId = appId; }

	@Column(name = "tempPassword", nullable = false)
	public String getTempPassword()
	{
		return tempPassword;
	}
	public void setTempPassword(String tempPassword)
	{
		this.tempPassword = tempPassword;
	}

	@Column(name = "expiryDate", nullable = false)
	public Date getExpiryDate()
	{
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	@Column(name = "lastRetrieved")
	public Date getLastRetrieved()
	{
		return lastRetrieved;
	}
	public void setLastRetrieved(Date lastRetrieved)
	{
		this.lastRetrieved = lastRetrieved;
	}

	@Column(name = "generationDate", nullable = false)
	public Date getGenerationDate()
	{
		return generationDate;
	}
	public void setGenerationDate(Date generationDate)
	{
		this.generationDate = generationDate;
	}
}

class UserPasswordComposite implements Serializable
{
	private static final long serialVersionUID = 4607875896783565676L;

	protected String entityRefId;
	protected EntityType entityType;
	protected String appId;

	public UserPasswordComposite() {}

	public UserPasswordComposite(String entityRefId, EntityType entityType, String appId)
	{
		this.entityRefId = entityRefId;
		this.entityType = entityType;
		this.appId = appId;
	}
}