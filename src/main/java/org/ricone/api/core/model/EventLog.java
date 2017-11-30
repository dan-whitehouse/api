package org.ricone.api.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventlog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonInclude(value= JsonInclude.Include.NON_EMPTY)
public class EventLog implements java.io.Serializable
{
    private static final long serialVersionUID = -2958634207891103587L;
	private String eventRefId;
	private EventType eventType;
	private LocalDateTime eventTimestamp;
	private Lea lea;
	private EventObject object;
	private String objectRefId;


	private School school;
	private SchoolCalendar calendar;
	private Course course;
	private CourseSection roster;
	private Staff staff;
	private Student student;
	private StudentContact contact;


	public EventLog()
	{
	}

	public EventLog(String eventRefId, EventType eventType, LocalDateTime  eventTimestamp, Lea lea, EventObject object, String objectRefId)
	{
		this.eventRefId = eventRefId;
		this.eventType = eventType;
		this.eventTimestamp = eventTimestamp;
		this.lea = lea;
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
	@Convert(converter = EventTypeConverter.class)
	public EventType getEventType()
	{
		return this.eventType;
	}
	public void setEventType(EventType eventType)
	{
		this.eventType = eventType;
	}

	@Column(name = "EventTimestamp", nullable = false, length = 19)
	public LocalDateTime  getEventTimestamp()
	{
		return this.eventTimestamp;
	}
	public void setEventTimestamp(LocalDateTime  eventTimestamp) { this.eventTimestamp = eventTimestamp; }

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEARefId", nullable = false)
	public Lea getLea()
	{
		return this.lea;
	}
	public void setLea(Lea lea)
	{
		this.lea = lea;
	}

	@Column(name = "Object", nullable = false)
	@Convert(converter = EventObjectConverter.class)
	public EventObject getObject()
	{
		return this.object;
	}
	public void setObject(EventObject object)
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


	@Transient
	public School getSchool() { return school; }
	public void setSchool(School school) { this.school = school; }

	@Transient
	public SchoolCalendar getCalendar() { return calendar; }
	public void setCalendar(SchoolCalendar calendar) { this.calendar = calendar; }

	@Transient
	public Course getCourse() { return course; }
	public void setCourse(Course course) { this.course = course; }

	@Transient
	public CourseSection getRoster() { return roster; }
	public void setRoster(CourseSection roster) { this.roster = roster; }

	@Transient
	public Staff getStaff() { return staff; }
	public void setStaff(Staff staff) { this.staff = staff; }

	@Transient
	public Student getStudent() { return student; }
	public void setStudent(Student student) { this.student = student; }

	@Transient
	public StudentContact getContact() { return contact; }
	public void setContact(StudentContact contact) { this.contact = contact; }

	@Override
	public String toString() {
		return "EventLog{" +
				"eventRefId='" + eventRefId + '\'' +
				", eventType='" + eventType + '\'' +
				", eventTimestamp=" + eventTimestamp +
				", lea='" + lea + '\'' +
				", object='" + object + '\'' +
				", objectRefId='" + objectRefId + '\'' +
				'}';
	}
}