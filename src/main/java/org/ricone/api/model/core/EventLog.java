package org.ricone.api.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author      Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version     0.8.1
 * @since       2015-08-07 
 */
 
@Entity
@Table(name = "eventlog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class EventLog implements java.io.Serializable
{
    private static final long serialVersionUID = -2958634207891103587L;
	private String eventRefId;
	private String eventType;
	private LocalDateTime eventTimestamp;
	private String leaRefId;
	private String object;
	private String objectRefId;

	public EventLog()
	{
	}

	public EventLog(String eventRefId, String eventType, LocalDateTime  eventTimestamp, String learefId, String object, String objectRefId)
	{
		this.eventRefId = eventRefId;
		this.eventType = eventType;
		this.eventTimestamp = eventTimestamp;
		this.leaRefId = learefId;
		this.object = object;
		this.objectRefId = objectRefId;
	}

	@Id
	@Column(name = "EventRefId", unique = true, nullable = false, length = 64)
	public String getEventRefId()
	{
		return this.eventRefId;
	}
	public void setEventRefId(String eventRefId)
	{
		this.eventRefId = eventRefId;
	}

	@Column(name = "EventType", nullable = false, length = 30)
	public String getEventType()
	{
		return this.eventType;
	}
	public void setEventType(String eventType)
	{
		this.eventType = eventType;
	}

	@Column(name = "EventTimestamp", nullable = false, length = 19)
	public LocalDateTime  getEventTimestamp()
	{
		return this.eventTimestamp;
	}
	public void setEventTimestamp(LocalDateTime  eventTimestamp) { this.eventTimestamp = eventTimestamp; }

	@Column(name = "LEARefId", nullable = false, length = 64)
	public String getLeaRefId()
	{
		return this.leaRefId;
	}
	public void setLeaRefId(String learefId)
	{
		this.leaRefId = learefId;
	}

	@Column(name = "Object", nullable = false)
	public String getObject()
	{
		return this.object;
	}
	public void setObject(String object)
	{
		this.object = object;
	}

	@Column(name = "ObjectRefId", nullable = false, length = 64)
	public String getObjectRefId()
	{
		return this.objectRefId;
	}
	public void setObjectRefId(String objectRefId)
	{
		this.objectRefId = objectRefId;
	}
}