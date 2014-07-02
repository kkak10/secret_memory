package org.ebj.vo;

public class MapMarkerVOAdd extends MapMarkerVO{

	private String groupName;
	
	public MapMarkerVOAdd() {
		super();
	}
	
	public MapMarkerVOAdd(Integer groupListNo, Integer categoryNo,
			Double markerX, Double markerY, String markerAddress) {
		super(groupListNo, categoryNo, markerX, markerY, markerAddress);
	}



	public MapMarkerVOAdd(Integer markerNo, Integer groupListNo,
			Integer categoryNo, Double markerX, Double markerY,
			String markerAddress, String markerCreateDate, String markerUser,
			String markerNickname, String groupName) {
		super(markerNo, groupListNo, categoryNo, markerX, markerY, markerAddress,
				markerCreateDate, markerUser, markerNickname);
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "MapMarkerVOAdd [groupName=" + groupName + ", getMarkerNo()="
				+ getMarkerNo() + ", getGroupListNo()=" + getGroupListNo()
				+ ", getCategoryNo()=" + getCategoryNo() + ", getMarkerX()="
				+ getMarkerX() + ", getMarkerY()=" + getMarkerY()
				+ ", getMarkerAddress()=" + getMarkerAddress()
				+ ", getMarkerCreateDate()=" + getMarkerCreateDate()
				+ ", getMarkerUser()=" + getMarkerUser()
				+ ", getMarkerNickname()=" + getMarkerNickname()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}
