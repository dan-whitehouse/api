package org.ricone.api.model.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "entryexitcode")
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class EntryExitCode implements java.io.Serializable
{
	private static final long serialVersionUID = -6732821891680730343L;
	private String entryExitCodeRefId;
	private StudentEnrollment studentEnrollment;
	private String systemTypeCode;
	private String entryExitType;
	private String code;

	public EntryExitCode()
	{
		
	}

	public EntryExitCode(String entryExitCodeRefId)
	{
		this.entryExitCodeRefId = entryExitCodeRefId;
	}


	public EntryExitCode(String entryExitCodeRefId, StudentEnrollment studentEnrollment, String systemTypeCode, String entryExitType, String code)
	{
		this.entryExitCodeRefId = entryExitCodeRefId;
		this.studentEnrollment = studentEnrollment;
		this.systemTypeCode = systemTypeCode;
		this.entryExitType = entryExitType;
		this.code = code;
	}

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "EntryExitCodeRefId", unique = true, nullable = false, length = 64)
	public String getEntryExitCodeRefId()
	{
		return entryExitCodeRefId;
	}

	public void setEntryExitCodeRefId(String entryExitCodeRefId)
	{
		this.entryExitCodeRefId = entryExitCodeRefId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StudentEnrollmentRefId")
	public StudentEnrollment getStudentEnrollment()
	{
		return studentEnrollment;
	}
	
	public void setStudentEnrollment(StudentEnrollment studentEnrollment)
	{
		this.studentEnrollment = studentEnrollment;
	}

	@Column(name = "SystemTypeCode", length = 50)
	public String getSystemTypeCode()
	{
		return systemTypeCode;
	}

	public void setSystemTypeCode(String systemTypeCode)
	{
		this.systemTypeCode = systemTypeCode;
	}
	
	@Column(name = "EntryExitType", length = 50)
	public String getEntryExitType()
	{
		return entryExitType;
	}

	public void setEntryExitType(String entryExitType)
	{
		this.entryExitType = entryExitType;
	}
	
	@Column(name = "Code", length = 50)
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}