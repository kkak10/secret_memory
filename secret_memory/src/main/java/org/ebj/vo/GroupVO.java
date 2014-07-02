package org.ebj.vo;

public class GroupVO {

	///Field
	private Integer groupNo;
	private Integer groupListNo;
	private String groupUser;
	private String groupRegDate;
	private String groupUserGrade;
	
	///Constructor
	public GroupVO() {
		super();
	}
	
	public GroupVO(String groupUser) {
		super();
		this.groupUser = groupUser;
	}
	
	public GroupVO(Integer groupListNo) {
		super();
		this.groupListNo = groupListNo;
	}

	public GroupVO(Integer groupListNo, String groupUser) {
		super();
		this.groupListNo = groupListNo;
		this.groupUser = groupUser;
	}

	public GroupVO(Integer groupListNo, String groupUser, String groupUserGrade) {
		super();
		this.groupListNo = groupListNo;
		this.groupUser = groupUser;
		this.groupUserGrade = groupUserGrade;
	}

	public GroupVO(Integer groupNo, Integer groupListNo, String groupUser,
			String groupRegDate, String groupUserGrade) {
		super();
		this.groupNo = groupNo;
		this.groupListNo = groupListNo;
		this.groupUser = groupUser;
		this.groupRegDate = groupRegDate;
		this.groupUserGrade = groupUserGrade;
	}
	
	///Method
	public Integer getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public Integer getGroupListNo() {
		return groupListNo;
	}

	public void setGroupListNo(Integer groupListNo) {
		this.groupListNo = groupListNo;
	}

	public String getGroupUser() {
		return groupUser;
	}

	public void setGroupUser(String groupUser) {
		this.groupUser = groupUser;
	}

	public String getGroupRegDate() {
		return groupRegDate;
	}

	public void setGroupRegDate(String groupRegDate) {
		this.groupRegDate = groupRegDate;
	}

	public String getGroupUserGrade() {
		return groupUserGrade;
	}

	public void setGroupUserGrade(String groupUserGrade) {
		this.groupUserGrade = groupUserGrade;
	}

	@Override
	public String toString() {
		return "GroupVO [groupNo=" + groupNo + ", groupListNo=" + groupListNo
				+ ", groupUser=" + groupUser + ", groupRegDate=" + groupRegDate
				+ ", groupUserGrade=" + groupUserGrade + "]";
	}

}
