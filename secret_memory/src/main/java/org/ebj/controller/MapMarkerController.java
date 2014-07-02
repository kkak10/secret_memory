package org.ebj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ebj.service.BoardService;
import org.ebj.service.MapMarkerService;
import org.ebj.vo.MapMarkerVO;
import org.ebj.vo.MapMarkerVOAdd;
import org.ebj.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/map")
public class MapMarkerController {

	protected static final Logger logger = Logger.getLogger(MapMarkerController.class);


	@Inject
	MapMarkerService mapMarkerService;
	
	@Inject
	BoardService boardService;

	///Field
	List<MapMarkerVO> markerList = new ArrayList<MapMarkerVO>();
	List<MapMarkerVOAdd> markerAddList = new ArrayList<MapMarkerVOAdd>();
	Map<String,String> checkMap = new HashMap<String,String>();

	@RequestMapping(value = "/selectMarker", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody List<MapMarkerVO> selectMarker(Integer groupListNo) {

		logger.info("============================");
		logger.info("selectMarker POST");
		logger.info("============================");

		try {
			logger.info(groupListNo);
			
			markerList = mapMarkerService.selectMarker(groupListNo);
			
			logger.info(markerList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("============================");
		
		return markerList;
	}
	
	
	@RequestMapping(value = "/createMarker", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody Map<String,String> createMarker(MapMarkerVO mapMarkerVO, HttpSession session){
		
		logger.info("============================");
		logger.info("createMarker POST");
		logger.info("============================");
		
		try {
			logger.info(mapMarkerVO);
			mapMarkerVO.setMarkerUser(((UserVO)session.getAttribute("dbUser")).getUserId());
			mapMarkerVO.setMarkerNickname(((UserVO)session.getAttribute("dbUser")).getUserNickname());
			mapMarkerService.createMarker(mapMarkerVO);
			checkMap.put("check", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkMap;
	}
	
	@RequestMapping(value = "/selectCategoryMarker", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody List<MapMarkerVO> selectCategoryMarker(MapMarkerVO mapMarkerVO) {
		
		logger.info("============================");
		logger.info("selectMarker POST");
		logger.info("============================");
		
		try {
			logger.info(mapMarkerVO);
			
			markerList = mapMarkerService.selectCategoryMarker(mapMarkerVO);
			
			logger.info(markerList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("============================");
		
		return markerList;
	}
	
	@RequestMapping(value = "/selectMarkerCheck", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody List<MapMarkerVOAdd> selectMarkerCheck(MapMarkerVO mapMarkerVO, HttpSession sesseion) {
		logger.info("============================");
		logger.info("markerCheck POST");
		logger.info("============================");
		logger.info("********************************************************************");
		logger.info(mapMarkerVO);
		
		List<MapMarkerVOAdd> alarmMarkerList = new ArrayList<MapMarkerVOAdd>();
		UserVO userVO = (UserVO)sesseion.getAttribute("dbUser");
		
		try {
			if (mapMarkerVO.getMarkerUser() != null) {
				markerAddList = mapMarkerService.selectMarkerCheck(userVO.getUserNickname());				
			} else {
				
			}
			logger.info(markerAddList);
			
		    double d2r = Math.PI / 180;
			double nowMarkerX = mapMarkerVO.getMarkerX();
			double nowMarkerY = mapMarkerVO.getMarkerY();
			double dbMarkerX = 0;
			double dbMarkerY = 0;
			double x = 0;
			double y = 0;
			double a = 0;
			double c = 0;
			double distance = 0;
			
			for (int i = 0; i < markerAddList.size(); i++) {
				
				dbMarkerX = markerAddList.get(i).getMarkerX();
				dbMarkerY = markerAddList.get(i).getMarkerY();
				
				x = (dbMarkerX - nowMarkerX) * d2r;
				y = (dbMarkerY - nowMarkerY) * d2r;
				
				a = Math.pow(Math.sin(y / 2.0), 2)
						+ Math.cos(nowMarkerY * d2r) * Math.cos(dbMarkerY * d2r)
						* Math.pow(Math.sin(x / 2.0), 2);
				
				c = Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)) * 2;
				
				distance = c * 6378 * 1000;
				
				logger.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				logger.info(distance);
				logger.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			
				if (distance <= 100) {
					alarmMarkerList.add(markerAddList.get(i));
				}
			}	
			
			logger.info("***********" + alarmMarkerList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return alarmMarkerList;
	}
	
	@RequestMapping(value = "/selectMapMarkerDeleteUser", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody Map<String,String> selectMapMarkerDeleteUser(String mapMarkerUser, HttpSession session){
		
		logger.info("============================");
		logger.info("selectMapMarkerDeleteUser POST");
		logger.info("============================");
		
		try {
			if(((UserVO)session.getAttribute("dbUser")).getUserId().equals(mapMarkerUser)){
				logger.info(mapMarkerUser + " == " + ((UserVO)session.getAttribute("dbUser")).getUserId());
				
				checkMap.put("check", "SUCCESS");
			}else{
				logger.info(mapMarkerUser + " != " + ((UserVO)session.getAttribute("dbUser")).getUserId());
				logger.info("아이디가 다름");
				checkMap.put("check", "FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkMap;
	}
}
