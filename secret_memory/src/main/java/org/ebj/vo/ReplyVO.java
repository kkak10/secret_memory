package org.ebj.vo;

public class ReplyVO {
	private Integer replyNo;
	private Integer boardNo;
	private String replyUser;
	private String replyNickname;
	private String replyText;
	private String replyRegDate;
	
	public ReplyVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReplyVO(Integer boardNo, String replyUser, String replyNickname,
			String replyText) {
		super();
		this.boardNo = boardNo;
		this.replyUser = replyUser;
		this.replyNickname = replyNickname;
		this.replyText = replyText;
	}

	public ReplyVO(Integer replyNo, Integer boardNo, String replyUser,
			String replyNickname, String replyText, String replyRegDate) {
		super();
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.replyUser = replyUser;
		this.replyNickname = replyNickname;
		this.replyText = replyText;
		this.replyRegDate = replyRegDate;
	}

	public Integer getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(Integer replyNo) {
		this.replyNo = replyNo;
	}

	public Integer getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}

	public String getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(String replyUser) {
		this.replyUser = replyUser;
	}

	public String getReplyNickname() {
		return replyNickname;
	}

	public void setReplyNickname(String replyNickname) {
		this.replyNickname = replyNickname;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public String getReplyRegDate() {
		return replyRegDate;
	}

	public void setReplyRegDate(String replyRegDate) {
		this.replyRegDate = replyRegDate;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", boardNo=" + boardNo
				+ ", replyUser=" + replyUser + ", replyNickname="
				+ replyNickname + ", replyText=" + replyText
				+ ", replyRegDate=" + replyRegDate + "]";
	}
}
