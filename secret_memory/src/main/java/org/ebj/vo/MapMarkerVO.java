package org.ebj.vo;

public class MapMarkerVO {

	private Integer markerNo;
	private Integer groupListNo;
	private Integer categoryNo;
	private Double markerX;
	private Double markerY;
	private String markerAddress;
	private String markerCreateDate;
	private String markerUser;
	private String markerNickname;
	
	public MapMarkerVO() {
		super();
	}

	public MapMarkerVO(Integer groupListNo, Integer categoryNo, Double markerX,
			Double markerY, String markerAddress) {
		super();
		this.groupListNo = groupListNo;
		this.categoryNo = categoryNo;
		this.markerX = markerX;
		this.markerY = markerY;
		this.markerAddress = markerAddress;
	}

	public MapMarkerVO(Integer markerNo, Integer groupListNo,
			Integer categoryNo, Double markerX, Double markerY,
			String markerAddress, String markerCreateDate, String markerUser,
			String markerNickname) {
		super();
		this.markerNo = markerNo;
		this.groupListNo = groupListNo;
		this.categoryNo = categoryNo;
		this.markerX = markerX;
		this.markerY = markerY;
		this.markerAddress = markerAddress;
		this.markerCreateDate = markerCreateDate;
		this.markerUser = markerUser;
		this.markerNickname = markerNickname;
	}

	public Integer getMarkerNo() {
		return markerNo;
	}

	public void setMarkerNo(Integer markerNo) {
		this.markerNo = markerNo;
	}

	public Integer getGroupListNo() {
		return groupListNo;
	}

	public void setGroupListNo(Integer groupListNo) {
		this.groupListNo = groupListNo;
	}

	public Integer getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Integer categoryNo) {
		this.categoryNo = categoryNo;
	}

	public Double getMarkerX() {
		return markerX;
	}

	public void setMarkerX(Double markerX) {
		this.markerX = markerX;
	}

	public Double getMarkerY() {
		return markerY;
	}

	public void setMarkerY(Double markerY) {
		this.markerY = markerY;
	}

	public String getMarkerAddress() {
		return markerAddress;
	}

	public void setMarkerAddress(String markerAddress) {
		this.markerAddress = markerAddress;
	}

	public String getMarkerCreateDate() {
		return markerCreateDate;
	}

	public void setMarkerCreateDate(String markerCreateDate) {
		this.markerCreateDate = markerCreateDate;
	}

	public String getMarkerUser() {
		return markerUser;
	}

	public void setMarkerUser(String markerUser) {
		this.markerUser = markerUser;
	}

	public String getMarkerNickname() {
		return markerNickname;
	}

	public void setMarkerNickname(String markerNickname) {
		this.markerNickname = markerNickname;
	}

	@Override
	public String toString() {
		return "MapMarkerVO [markerNo=" + markerNo + ", groupListNo="
				+ groupListNo + ", categoryNo=" + categoryNo + ", markerX="
				+ markerX + ", markerY=" + markerY + ", markerAddress="
				+ markerAddress + ", markerCreateDate=" + markerCreateDate
				+ ", markerUser=" + markerUser + ", markerNickname="
				+ markerNickname + "]";
	}

}
