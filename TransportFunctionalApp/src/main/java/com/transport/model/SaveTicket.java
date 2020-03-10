package com.transport.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ticket")
public class SaveTicket {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name = "ticket_id")
    private String ticketId;
	@Column(name = "user_id")
    private String userId;
	@Column(name = "raised_by")
    private int raisedBy;
	@Column(name = "ticket_type")
    private int ticketType;
	@Column(name = "ticket_description")
    private String ticketDescription;
	@Column(name = "raised_on")
    private Date raisedOn;
	@Column(name = "assigned_on")
    private Date assignedOn;
	@Column(name = "assigned_to")
    private String assignedTo;
	@Column(name = "assignee_email")
    private String assigneeEmail;
	@Column(name = "assigned_description")
    private String assignedDescription;
	@Column(name = "resolved_on")
    private Date resolvedOn;
	@Column(name = "resolved_description")
    private String resolvedDescription;
	@Column(name = "edited_on")
    private String editON;
	@Column(name = "ticket_status")
    private int ticketStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRaisedBy() {
		return raisedBy;
	}
	public void setRaisedBy(int raisedBy) {
		this.raisedBy = raisedBy;
	}
	public int getTicketType() {
		return ticketType;
	}
	public void setTicketType(int ticketType) {
		this.ticketType = ticketType;
	}
	public String getTicketDescription() {
		return ticketDescription;
	}
	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}
	public Date getRaisedOn() {
		return raisedOn;
	}
	public void setRaisedOn(Date raisedOn) {
		this.raisedOn = raisedOn;
	}
	public Date getAssignedOn() {
		return assignedOn;
	}
	public void setAssignedOn(Date assignedOn) {
		this.assignedOn = assignedOn;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getAssigneeEmail() {
		return assigneeEmail;
	}
	public void setAssigneeEmail(String assigneeEmail) {
		this.assigneeEmail = assigneeEmail;
	}
	public String getAssignedDescription() {
		return assignedDescription;
	}
	public void setAssignedDescription(String assignedDescription) {
		this.assignedDescription = assignedDescription;
	}
	public Date getResolvedOn() {
		return resolvedOn;
	}
	public void setResolvedOn(Date resolvedOn) {
		this.resolvedOn = resolvedOn;
	}
	public String getResolvedDescription() {
		return resolvedDescription;
	}
	public void setResolvedDescription(String resolvedDescription) {
		this.resolvedDescription = resolvedDescription;
	}
	public String getEditON() {
		return editON;
	}
	public void setEditON(String editON) {
		this.editON = editON;
	}
	public int getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(int ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	
	
	
	
}
