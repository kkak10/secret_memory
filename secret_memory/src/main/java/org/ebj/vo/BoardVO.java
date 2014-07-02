package org.ebj.vo;

public class BoardVO {
	private Integer boardNo;
	private String boardUser;
	private Integer categoryNo;
	private Integer groupListNo;
	private Integer markerNo;
	private String boardText;
	private String boardNickname;
	private String boardRegDate;
	private String boardImage;
	
	public BoardVO() {
		super();
	}

	public BoardVO(String boardUser, Integer groupListNo, Integer markerNo,
			String boardText, String boardNickname) {
		super();
		this.boardUser = boardUser;
		this.groupListNo = groupListNo;
		this.markerNo = markerNo;
		this.boardText = boardText;
		this.boardNickname = boardNickname;
	}
	
	public BoardVO(String boardUser, Integer groupListNo, 
			String boardText, String boardNickname) {
		super();
		this.boardUser = boardUser;
		this.groupListNo = groupListNo;
		this.boardText = boardText;
		this.boardNickname = boardNickname;
	}


	public BoardVO(Integer boardNo, String boardUser, Integer categoryNo, Integer groupListNo,
			Integer markerNo, String boardText, String boardNickname,
			String boardRegDate, String boardImage) {
		super();
		this.boardNo = boardNo;
		this.boardUser = boardUser;
		this.categoryNo = categoryNo;
		this.groupListNo = groupListNo;
		this.markerNo = markerNo;
		this.boardText = boardText;
		this.boardNickname = boardNickname;
		this.boardRegDate = boardRegDate;
		this.boardImage = boardImage;
	}

	public Integer getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardUser() {
		return boardUser;
	}

	public void setBoardUser(String boardUser) {
		this.boardUser = boardUser;
	}
	
	
	public Integer getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Integer categoryNo) {
		this.categoryNo = categoryNo;
	}

	public Integer getGroupListNo() {
		return groupListNo;
	}

	public void setGroupListNo(Integer groupListNo) {
		this.groupListNo = groupListNo;
	}

	public Integer getMarkerNo() {
		return markerNo;
	}

	public void setMarkerNo(Integer markerNo) {
		this.markerNo = markerNo;
	}

	public String getBoardText() {
		return boardText;
	}

	public void setBoardText(String boardText) {
		this.boardText = boardText;
	}

	public String getBoardNickname() {
		return boardNickname;
	}

	public void setBoardNickname(String boardNickname) {
		this.boardNickname = boardNickname;
	}

	public String getBoardRegDate() {
		return boardRegDate;
	}

	public void setBoardRegDate(String boardRegDate) {
		this.boardRegDate = boardRegDate;
	}

	public String getBoardImage() {
		return boardImage;
	}

	public void setBoardImage(String boardImage) {
		this.boardImage = boardImage;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardUser=" + boardUser
				+ ", categoryNo=" + categoryNo + ", groupListNo=" + groupListNo
				+ ", markerNo=" + markerNo + ", boardText=" + boardText
				+ ", boardNickname=" + boardNickname + ", boardRegDate="
				+ boardRegDate + ", boardImage=" + boardImage + "]";
	}

}
