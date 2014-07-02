package org.ebj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ebj.service.CalendarService;
import org.ebj.vo.CalendarVO;
import org.ebj.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

	protected final static Logger logger = Logger.getLogger(UserController.class);

	@Inject
	private CalendarService calendarService;
	
	Map<String,String> checkMap = new HashMap<String,String>();
	
	@RequestMapping(value = "/insertCalendar" ,method = RequestMethod.POST, produces = "application/json")
	public  @ResponseBody Map<String,String> insertCalendar(CalendarVO calendarVO, HttpSession session) {

		logger.info("-------------------------------");
		logger.info("insertCalendar POST");
		logger.info("-------------------------------");
		UserVO userVO = (UserVO)session.getAttribute("dbUser");
			logger.info(userVO);
			logger.info(userVO.getUserNickname());
			logger.info(calendarVO.getCalendarText());
		String calendarText = userVO.getUserNickname() + " - " +   calendarVO.getCalendarText();
		
		calendarVO.setCalendarText(calendarText);
		
		calendarVO.setCalendarUser(((UserVO)session.getAttribute("dbUser")).getUserId());
		calendarVO.setCalendarNickname(((UserVO)session.getAttribute("dbUser")).getUserNickname());
		
		logger.info(calendarVO);
		
		try {
			calendarService.insertCalendar(calendarVO);
			
			checkMap.put("check", "SUCCESS");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkMap;
	}
	
	
	@RequestMapping(value = "/selectCalendar" ,method = RequestMethod.POST, produces = "application/json")
	public  @ResponseBody List<CalendarVO> selectCalendar(Integer groupListNo){
		List<CalendarVO> calendarList = new ArrayList<CalendarVO>();
		
		logger.info("-------------------------------");
		logger.info("SelectCalendar POST");
		logger.info("-------------------------------");
		
		try {
			
			calendarList = calendarService.selectCalendar(groupListNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return calendarList;
	}
	
}
