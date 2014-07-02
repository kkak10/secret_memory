package org.ebj.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ebj.email.MailService;
import org.ebj.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	protected final static Logger logger = Logger.getLogger(MailController.class);
	
	@Inject
	private MailService mailService;
	
	///Field
	Map<String,String> checkMap = new HashMap<String,String>();
	
	@RequestMapping(value="/groupInvite", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody Map<String,String>groupInvite(HttpSession session, String mailList, 
									Integer inviteGroupListNo,String selectGroupName){
		
		logger.info("===================");
		logger.info("groupInvite post");
		logger.info("===================");
		
		String addMail = mailList.trim();
		
		logger.info(addMail);
		logger.info(inviteGroupListNo);

		String[] mails = addMail.split("<br>");
		String mailTextStart = "<a href='http://121.160.208.118:8080/mail/inviteGroupListNo?inviteGroupListNo=" + inviteGroupListNo + "'>";
		String mialTextMid = ((UserVO)session.getAttribute("dbUser")).getUserId() + " 님께서 " + selectGroupName + " 그룹에 초대하셨습니다.";
		String mailTextEnd	="</a>";
		
		for(int i = 0; i < mails.length; i++){
			mailService.sendMail(mails[i], "SecretMemory입니다.", mailTextStart + mialTextMid + mailTextEnd);
		}
		
		return checkMap;
	}
	
	@RequestMapping(value="/inviteGroupListNo", method={RequestMethod.GET})
	public void inviteGroupListNo(HttpSession session, HttpServletRequest request,HttpServletResponse response){
		
		logger.info("======================");
		logger.info("inviteGroupListNo get");
		logger.info("======================");
		
		String inviteGroupListNo = request.getParameter("inviteGroupListNo");
		logger.info(inviteGroupListNo);
		session.setAttribute("inviteGroupListNo", inviteGroupListNo);
		
		try {
			response.sendRedirect("/resources/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
}
