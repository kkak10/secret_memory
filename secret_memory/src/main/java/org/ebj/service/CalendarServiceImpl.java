package org.ebj.service;

import java.util.List;

import javax.inject.Inject;

import org.ebj.mapper.CalendarMapper;
import org.ebj.vo.CalendarVO;
import org.springframework.stereotype.Service;

@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {


	@Inject
	private CalendarMapper calendarMapper;
	
	@Override
	public void insertCalendar(CalendarVO calendarVO) throws Exception {
		calendarMapper.insertCalendar(calendarVO);
	}

	@Override
	public List<CalendarVO> selectCalendar(Integer groupListNo)	throws Exception {
		return calendarMapper.selectCalendar(groupListNo);
	}

}
