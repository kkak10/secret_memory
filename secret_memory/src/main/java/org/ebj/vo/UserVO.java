package org.ebj.vo;

public class UserVO {

	///Field
	private String userId;
	private String userPw;
	private String userName;
	private String userNickname;
	private String userRegDate;
	private String userImage;
	
	///Constructor
	public UserVO() {
		super();
	}

	public UserVO(String userId, String userPw, String userName,
			String userNickname) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userNickname = userNickname;
	}

	public UserVO(String userId, String userPw, String userName,
			String userNickname, String userRegDate, String userImage) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userNickname = userNickname;
		this.userRegDate = userRegDate;
		this.userImage = userImage;
	}

	///Method
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPw=" + userPw
				+ ", userName=" + userName + ", userNickname=" + userNickname
				+ ", userRegDate=" + userRegDate + ", userImage=" + userImage
				+ "]";
	}
	
}
