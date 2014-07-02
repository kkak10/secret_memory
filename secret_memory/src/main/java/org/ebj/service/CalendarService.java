package org.ebj.service;

import java.util.List;

import org.ebj.vo.CalendarVO;

public interface CalendarService {

	public void insertCalendar(CalendarVO calendarVO) throws Exception;
	
	public List<CalendarVO> selectCalendar(Integer groupListNo) throws Exception;  
	
}
