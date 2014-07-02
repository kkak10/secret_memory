package org.ebj.vo;

public class CalendarVO {

	private Integer calendarNo;
	private Integer groupListNo;
	private String calendarUser;
	private String calendarNickname;
	private String calendarText;
	private Integer calendarYear;
	private Integer calendarMonth;
	private Integer calendarDay;
	private String calendarColor;
	
	public CalendarVO() {
		super();
	}

	public CalendarVO(Integer calendarNo, Integer groupListNo,
			String calendarUser, String calendarNickname, String calendarText,
			Integer calendarYear, Integer calendarMonth, Integer calendarDay,
			String calendarColor) {
		super();
		this.calendarNo = calendarNo;
		this.groupListNo = groupListNo;
		this.calendarUser = calendarUser;
		this.calendarNickname = calendarNickname;
		this.calendarText = calendarText;
		this.calendarYear = calendarYear;
		this.calendarMonth = calendarMonth;
		this.calendarDay = calendarDay;
		this.calendarColor = calendarColor;
	}

	public Integer getCalendarNo() {
		return calendarNo;
	}

	public void setCalendarNo(Integer calendarNo) {
		this.calendarNo = calendarNo;
	}

	public Integer getGroupListNo() {
		return groupListNo;
	}

	public void setGroupListNo(Integer groupListNo) {
		this.groupListNo = groupListNo;
	}

	public String getCalendarUser() {
		return calendarUser;
	}

	public void setCalendarUser(String calendarUser) {
		this.calendarUser = calendarUser;
	}

	public String getCalendarNickname() {
		return calendarNickname;
	}

	public void setCalendarNickname(String calendarNickname) {
		this.calendarNickname = calendarNickname;
	}

	public String getCalendarText() {
		return calendarText;
	}

	public void setCalendarText(String calendarText) {
		this.calendarText = calendarText;
	}

	public Integer getCalendarYear() {
		return calendarYear;
	}

	public void setCalendarYear(Integer calendarYear) {
		this.calendarYear = calendarYear;
	}

	public Integer getCalendarMonth() {
		return calendarMonth;
	}

	public void setCalendarMonth(Integer calendarMonth) {
		this.calendarMonth = calendarMonth;
	}

	public Integer getCalendarDay() {
		return calendarDay;
	}

	public void setCalendarDay(Integer calendarDay) {
		this.calendarDay = calendarDay;
	}

	public String getCalendarColor() {
		return calendarColor;
	}

	public void setCalendarColor(String calendarColor) {
		this.calendarColor = calendarColor;
	}

	@Override
	public String toString() {
		return "CalendarVO [calendarNo=" + calendarNo + ", groupListNo="
				+ groupListNo + ", calendarUser=" + calendarUser
				+ ", calendarNickname=" + calendarNickname + ", calendarText="
				+ calendarText + ", calendarYear=" + calendarYear
				+ ", calendarMonth=" + calendarMonth + ", calendarDay="
				+ calendarDay + ", calendarColor=" + calendarColor + "]";
	}
	
	
}
	