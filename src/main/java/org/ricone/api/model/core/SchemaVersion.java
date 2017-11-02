package org.ricone.api.model.core;

import javax.persistence.*;
import java.util.Date;

/**
 * @author      Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version     1.0.0.2
 * @since       2016-08-03 
 */
 
@Entity
@Table(name = "schemaversion")
@IdClass(SchemaVersionPK.class)
public class SchemaVersion implements java.io.Serializable 
{
    private static final long serialVersionUID = -5220447978182945197L;
	@Id
	private Integer major;
	@Id
	private Integer minor;
	@Id
	private Integer bugFix;
	private String description;
	private Date dateApplied; 

	public SchemaVersion()
	{
	}

	public SchemaVersion(Integer major, Integer minor, Integer bugFix, String description, Date dateApplied)
	{
		super();
		this.major = major;
		this.minor = minor;
		this.bugFix = bugFix;
		this.description = description;
		this.dateApplied = dateApplied;
	}

	@Column(name = "Major")
	public Integer getMajor()
	{
		return major;
	}
	public void setMajor(Integer major)
	{
		this.major = major;
	}

	@Column(name = "Minor")
	public Integer getMinor()
	{
		return minor;
	}
	public void setMinor(Integer minor)
	{
		this.minor = minor;
	}

	@Column(name = "BugFix")
	public Integer getBugFix()
	{
		return bugFix;
	}
	public void setBugFix(Integer bugFix)
	{
		this.bugFix = bugFix;
	}

	@Column(name = "Description")
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DateApplied")
	public Date getDateApplied()
	{
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied)
	{
		this.dateApplied = dateApplied;
	}

	@Transient
	public String getVersion()
	{
		return major + "." + minor + "." + bugFix;
	}
}