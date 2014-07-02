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
import org.ebj.vo.BoardVO;
import org.ebj.vo.BoardVOAdd;
import org.ebj.vo.MapMarkerVO;
import org.ebj.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	protected static final Logger logger = Logger.getLogger(BoardController.class);
	
	@Inject
	MapMarkerService mapMarkerService;
	
	@Inject
	BoardService boardService;
	
	///Field
	List<MapMarkerVO> markerList = new ArrayList<MapMarkerVO>();
	Map<String,String> checkMap = new HashMap<String,String>();

	@RequestMapping(value = "/insertMapBoard", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody Map<String,String> insertMapBoard(BoardVO boardVO, HttpSession session) {
		
		logger.info("============================");
		logger.info("insertBoard POST");
		logger.info("============================");
		UserVO userVO = (UserVO) session.getAttribute("dbUser");

		try {
			boardVO.setBoardUser(userVO.getUserId());
			boardVO.setMarkerNo(mapMarkerService.selectLastMarkerNo());
			boardVO.setBoardNickname(userVO.getUserNickname());
			logger.info(boardVO);
			boardService.insertMarkerBoard(boardVO);
			checkMap.put("check", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkMap;

	}
	
	@RequestMapping(value = "/insertBoard", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody Map<String,String> insertBoard(BoardVO boardVO,HttpSession session) {
		
		logger.info("============================");
		logger.info("insertBoard POST");
		logger.info("============================");
		
		try {
			boardVO.setBoardUser(((UserVO) session.getAttribute("dbUser")).getUserId());
			boardVO.setBoardNickname(((UserVO) session.getAttribute("dbUser")).getUserNickname());
			logger.info(boardVO);
			boardService.insertNoMarkerBoard(boardVO);
			checkMap.put("check", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("insertBoard POST END");
		return checkMap;
	}
	
	@RequestMapping(value = "/selectBoardList", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody List<BoardVOAdd> selectBoardList(BoardVOAdd boardVOAdd) {
		List<BoardVOAdd> boardList = new ArrayList<BoardVOAdd>();
		logger.info("============================");
		logger.info("selectBoardList POST");
		logger.info("============================");

		try {
			checkMap.put("check", "SUCCESS");
			
			boardList = boardService.selectBoardList(boardVOAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardList;

	}
	
	@RequestMapping(value = "/selectBoard", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody BoardVOAdd selectBoard(MapMarkerVO mapMarkerVO) {
		logger.info("============================");
		logger.info("selectBoard POST");
		logger.info("============================");
			
		logger.info("++++++++++++++++++++++++++++++++++++++++++" + mapMarkerVO);
		BoardVOAdd boardVOAdd = null;
			
		try {
			boardVOAdd = boardService.selectBoard(mapMarkerVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("++++++++++++++++++++++++++++++++++++++++++" + boardVOAdd);
		return boardVOAdd;
	}
	
	@RequestMapping(value ="/selectBoardImage", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<BoardVO> selectBoardImage(Integer groupListNo){
		
		logger.info("============================");
		logger.info("selectBoardImage POST");
		logger.info("============================");
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			
			boardList = boardService.selectBoardImage(groupListNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardList;
	}
	
	
	@RequestMapping(value ="/deleteMarkerBoard", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String,String> deleteMarkerBoard(MapMarkerVO mapMarkerVO){
		
		logger.info("=======================");
		logger.info("deleteMarkerBoard post");
		logger.info("=======================");
		logger.info("mapMarkerVO = " + mapMarkerVO);
		
		try {
			
			if(boardService.selectBoard(mapMarkerVO) != null){
				boardService.deleteMarkerBoard(mapMarkerVO);
			}
			mapMarkerService.deleteMarker(mapMarkerVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkMap;	
	}
	
	@RequestMapping(value ="/deleteBoard", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String,String> deleteBoard(Integer boardNo){
		
		logger.info("=======================");
		logger.info("deleteBoard post");
		logger.info("=======================");
		logger.info("BoardNo = " + boardNo);
		
		try {
			if(boardService.selectBoardMakerNo(boardNo) != null){
				Integer markerNo = boardService.selectBoardMakerNo(boardNo);
				boardService.deleteBoard(boardNo);
				mapMarkerService.deleteMarkerNo(markerNo);
			}else{
				boardService.deleteBoard(boardNo);	
			}
			checkMap.put("check", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkMap;	
	}
	
	@RequestMapping(value ="/selectBoardDeleteUser", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String,String> selectBoardDeleteUser(HttpSession session){
		
		logger.info("=======================");
		logger.info("selectBoardDeleteUser post");
		logger.info("=======================");
		
		try {
			checkMap.put("boardUser", ((UserVO) session.getAttribute("dbUser")).getUserId());
			checkMap.put("check", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkMap;	
	}
	
}
