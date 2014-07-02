package org.ebj.vo;

public class GroupListVO {
	
	///Field
	private Integer groupListNo;
	private String groupMaster;
	private String groupName;
	private String groupRename;
	private String groupCreateDate;
	private String groupUserGrade;
	private String groupImage;

	///Constructor
	public GroupListVO() {
		super();
	}

	public GroupListVO(String groupMaster) {
		super();
		this.groupMaster = groupMaster;
	}

	public GroupListVO(String groupMaster, String groupName) {
		super();
		this.groupMaster = groupMaster;
		this.groupName = groupName;
	}
	
	public GroupListVO(String groupMaster, Integer groupListNo) {
		super();
		this.groupMaster = groupMaster;
		this.groupListNo = groupListNo;
	}

	public GroupListVO(String groupMaster, String groupName, String groupRename) {
		super();
		this.groupMaster = groupMaster;
		this.groupName = groupName;
		this.groupRename = groupRename;
	}
	
	public GroupListVO(Integer groupListNo, String groupName,
			String groupUserGrade) {
		super();
		this.groupListNo = groupListNo;
		this.groupName = groupName;
		this.groupUserGrade = groupUserGrade;
	}

	public GroupListVO(Integer groupListNo, String groupMaster,
			String groupName, String groupRename, String groupCreateDate,
			String groupUserGrade, String groupImage) {
		super();
		this.groupListNo = groupListNo;
		this.groupMaster = groupMaster;
		this.groupName = groupName;
		this.groupRename = groupRename;
		this.groupCreateDate = groupCreateDate;
		this.groupUserGrade = groupUserGrade;
		this.groupImage = groupImage;
	}

	///Method
	public Integer getGroupListNo() {
		return groupListNo;
	}

	public void setGroupListNo(Integer groupListNo) {
		this.groupListNo = groupListNo;
	}

	public String getGroupMaster() {
		return groupMaster;
	}

	public void setGroupMaster(String groupMaster) {
		this.groupMaster = groupMaster;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupRename() {
		return groupRename;
	}

	public void setGroupRename(String groupRename) {
		this.groupRename = groupRename;
	}

	public String getGroupCreateDate() {
		return groupCreateDate;
	}

	public String getGroupUserGrade() {
		return groupUserGrade;
	}
	
	public void setGroupUserGrade(String groupUserGrade) {
		this.groupUserGrade = groupUserGrade;
	}
	
	public void setGroupCreateDate(String groupCreateDate) {
		this.groupCreateDate = groupCreateDate;
	}
	
	public String getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}

	@Override
	public String toString() {
		return "GroupListVO [groupListNo=" + groupListNo + ", groupMaster="
				+ groupMaster + ", groupName=" + groupName + ", groupRename="
				+ groupRename + ", groupCreateDate=" + groupCreateDate
				+ ", groupUserGrade=" + groupUserGrade + ", groupImage="
				+ groupImage + "]";
	}
}
