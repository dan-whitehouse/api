package org.ricone.api.core.dao;

public class SchemaVersionPK implements java.io.Serializable
{
    private static final long serialVersionUID = -5220447978182945197L;
	protected Integer major;
	protected Integer minor;
	protected Integer bugFix;
	
	public SchemaVersionPK(){}

	public SchemaVersionPK(Integer major, Integer minor, Integer bugFix)
	{
		this.major = major;
		this.minor = minor;
		this.bugFix = bugFix;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bugFix == null) ? 0 : bugFix.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((minor == null) ? 0 : minor.hashCode());
		return result;
	}
}