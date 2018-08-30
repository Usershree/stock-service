package com.hb.model;


// Generated Dec 5, 2017 9:22:12 AM by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * InviteReqSentToFrom generated by hbm2java
 */
@Entity
@Table(name = "invite_req_sent_to_from", catalog = "houseboard")
public class InviteReqSentToFrom implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3996618662004088530L;
	private Integer id;
	private GroupDetails groupDetails;
	private SignUp signUp;
	private String reqSentTo;
	private String reqAcceptedRejected;

	public InviteReqSentToFrom() {
	}

	public InviteReqSentToFrom(GroupDetails groupDetails, SignUp signUp, String reqSentTo, String reqAcceptedRejected) {
		this.groupDetails = groupDetails;
		this.signUp = signUp;
		this.reqSentTo = reqSentTo;
		this.reqAcceptedRejected = reqAcceptedRejected;
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
	@JoinColumn(name = "REQ_SENT_FROM")
	public SignUp getSignUp() {
		return this.signUp;
	}

	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
	}

	@Column(name = "REQ_SENT_TO", length = 100)
	public String getReqSentTo() {
		return this.reqSentTo;
	}

	public void setReqSentTo(String reqSentTo) {
		this.reqSentTo = reqSentTo;
	}

	@Column(name = "REQ_ACCEPTED_REJECTED", length = 45)
	public String getReqAcceptedRejected() {
		return this.reqAcceptedRejected;
	}

	public void setReqAcceptedRejected(String reqAcceptedRejected) {
		this.reqAcceptedRejected = reqAcceptedRejected;
	}

}
