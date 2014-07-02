package org.ebj.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ebj.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController{
	
	protected static final Logger logger = Logger.getLogger(ChatController.class);
	
	@RequestMapping(value ="/nickName", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public @ResponseBody String nickNameLoad(HttpSession session){
		
		logger.info("============================");
		logger.info("nickNameLoad POST");
		logger.info("============================");

		String nickName;
		
		UserVO userVO;
		
		userVO = (UserVO)session.getAttribute("dbUser");
		
		nickName = userVO.getUserNickname();
		
		logger.info("********************" + nickName);
		return nickName;
	}

}
