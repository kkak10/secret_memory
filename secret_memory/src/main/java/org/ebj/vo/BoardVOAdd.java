package org.ebj.vo;

public class BoardVOAdd extends BoardVO {

	private String userImage;
	private Integer pageNo;

	public BoardVOAdd() {
		super();
	}

	public BoardVOAdd(String boardUser, Integer groupListNo, String boardText,
			String boardNickname) {
		super(boardUser, groupListNo, boardText, boardNickname);
	}

	public BoardVOAdd(Integer boardNo, String boardUser, Integer categoryNo,
			Integer groupListNo, Integer markerNo, String boardText,
			String boardNickname, String boardRegDate, String boardImage,
			String userImage, Integer pageNo) {
		super(boardNo, boardUser, categoryNo, groupListNo, markerNo, boardText,
				boardNickname, boardRegDate, boardImage);
		this.userImage = userImage;
		this.pageNo = pageNo;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		return "BoardVOAdd [userImage=" + userImage + ", getBoardNo()="
				+ getBoardNo() + ", getBoardUser()=" + getBoardUser()
				+ ", getCategoryNo()=" + getCategoryNo()
				+ ", getGroupListNo()=" + getGroupListNo() + ", getMarkerNo()="
				+ getMarkerNo() + ", getBoardText()=" + getBoardText()
				+ ", getBoardNickname()=" + getBoardNickname()
				+ ", getBoardRegDate()=" + getBoardRegDate()
				+ ", getBoardImage()=" + getBoardImage() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "PageNo = " + getPageNo() + "]";
	}

}
