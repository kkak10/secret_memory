package org.ebj.mapper;

import java.util.List;

import org.ebj.vo.CalendarVO;

public interface CalendarMapper {

	public void insertCalendar(CalendarVO calendarVO) throws Exception;
	
	public List<CalendarVO> selectCalendar(Integer groupListNo) throws Exception;  
	
}
