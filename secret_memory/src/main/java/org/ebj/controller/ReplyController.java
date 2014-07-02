package org.ebj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ebj.service.ReplyService;
import org.ebj.vo.ReplyVO;
import org.ebj.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	protected static final Logger logger = Logger.getLogger(ReplyController.class);
	
	@Inject
	ReplyService replyService;

	///Field
	List<ReplyVO> replyList = new ArrayList<ReplyVO>();
	Map<String,String> checkMap = new HashMap<String,String>();

	@RequestMapping(value = "/selectReply", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody List<ReplyVO> selectReply(Integer boardNo) {
		
		logger.info("============================");
		logger.info("selectReply POST");
		logger.info("============================");
		
		logger.info("selectReply - " + boardNo);
		
		try {
			replyList=replyService.selectReply(boardNo);
			checkMap.put("check", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return replyList;
	}
	
	@RequestMapping(value = "/insertReply", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody Map<String,String> insertReply(ReplyVO replyVO,HttpSession session) {
		
		logger.info("============================");
		logger.info("insertReply POST");
		logger.info("============================");
		
		replyVO.setReplyUser(((UserVO)session.getAttribute("dbUser")).getUserId());
		replyVO.setReplyNickname(((UserVO)session.getAttribute("dbUser")).getUserNickname());
		
		logger.info("insertReply - " + replyVO);
		
		try {
			replyService.insertReply(replyVO);
			checkMap.put("check", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkMap;
	}
	
	@RequestMapping(value = "/deleteReply", method = { RequestMethod.POST }, produces = "application/json")
	public @ResponseBody Map<String,String> deleteReply(Integer replyNo) {
		
		logger.info("============================");
		logger.info("deleteReply POST");
		logger.info("============================");
		
		logger.info("deleteReply - " + replyNo);
		
		try {
			replyService.deleteReply(replyNo);
			checkMap.put("check", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkMap;
	}
	
}
