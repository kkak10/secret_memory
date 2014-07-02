package org.ebj.vo;

public class UserVOAdd extends UserVO{

	private String groupUserGrade;

	public UserVOAdd() {
		super();
	}

	public UserVOAdd(String userId, String userPw, String userName,
			String userNickname, String userRegDate, String userImage, String groupUserGrade) {
		super(userId, userPw, userName, userNickname, userRegDate, userImage);
		this.groupUserGrade = groupUserGrade;
	}

	public String getGroupUserGrade() {
		return groupUserGrade;
	}

	public void setGroupUserGrade(String groupUserGrade) {
		this.groupUserGrade = groupUserGrade;
	}

	@Override
	public String toString() {
		return "UserVOAdd [groupUserGrade=" + groupUserGrade + ", getUserId()="
				+ getUserId() + ", getUserPw()=" + getUserPw()
				+ ", getUserName()=" + getUserName() + ", getUserNickname()="
				+ getUserNickname() + ", getUserRegDate()=" + getUserRegDate()
				+ ", getUserImage()=" + getUserImage() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
}
