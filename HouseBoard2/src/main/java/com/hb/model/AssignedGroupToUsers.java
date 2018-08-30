package com.hb.model;


// Generated Dec 5, 2017 9:22:12 AM by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * AssignedGroupToUsers generated by hbm2java
 */
@Entity
@Table(name = "assigned_group_to_users", catalog = "houseboard")
public class AssignedGroupToUsers implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3914045883774973490L;
	private Integer id;
	private GroupDetails groupDetails;
	private SignUp signUp;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",timezone="IST")
	private Date dateTime;
	
	private static final SimpleDateFormat FORMATER = new SimpleDateFormat("MMM dd, yyyy hh:mm a z");

	public AssignedGroupToUsers() {
	}

	public AssignedGroupToUsers(GroupDetails groupDetails, SignUp signUp) {
		this.groupDetails = groupDetails;
		this.signUp = signUp;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID")
	public GroupDetails getGroupDetails() {
		return this.groupDetails;
	}

	public void setGroupDetails(GroupDetails groupDetails) {
		this.groupDetails = groupDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public SignUp getSignUp() {
		return this.signUp;
	}

	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_TIME", nullable = false, length = 19)
	public Date getDateTime() {
		if(dateTime == null) {
			try {
				dateTime = FORMATER.parse(FORMATER.format(new Date()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("get method dateTime is "+this.dateTime);
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		System.out.println("set method dateTime is "+dateTime);
		this.dateTime = dateTime;
	}

}
