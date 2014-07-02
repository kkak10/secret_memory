package org.ebj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ebj.service.GroupListService;
import org.ebj.service.GroupService;
import org.ebj.service.UserService;
import org.ebj.vo.GroupListVO;
import org.ebj.vo.GroupVO;
import org.ebj.vo.UserVO;
import org.ebj.vo.UserVOAdd;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	
	protected final static Logger logger = Logger.getLogger(UserController.class);

	@Inject
	private UserService userService;
	
	@Inject
	private GroupService groupService;
	
	@Inject
	private GroupListService groupListService;
	
	///Field
	UserVO dbUser = new UserVO();
	Map<String,String> checkMap = new HashMap<String,String>();
	
	@RequestMapping(value = "/loginCheck", produces = "application/json")
	public @ResponseBody Map<String,Object> loginCheck(HttpSession session, HttpServletRequest request) {
		
		logger.info("-------------------------------");
		logger.info("loginCheck");
		logger.info("-------------------------------");
		
		Map<String,Object> loginCheckMap = new HashMap<String,Object>();
		
		if (session.getAttribute("dbUser") != null) {
			loginCheckMap.put("check", "SUCCESS");
			loginCheckMap.put("dbUser", (UserVO)session.getAttribute("dbUser"));
			
			if (session.getAttribute("inviteGroupListNo") != null) {
				loginCheckMap.put("inviteGroupListNo","true");
			}
			
		} else {
			loginCheckMap.put("check", "FAIL");
		}
		
		return loginCheckMap;
	}
	
	@RequestMapping(value = "/login" ,method = RequestMethod.GET, produces = "application/json")
	public void login() {

		logger.info("-------------------------------");
		logger.info("login GET");
		logger.info("-------------------------------");
	}
	
	@RequestMapping(value ="/login" , method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String,Object> login(UserVO userVO, HttpSession session ) {
		
		logger.info("-------------------------------");
		logger.info("login POST");
		logger.info("-------------------------------");
		
		Map<String,Object> loginMap = new HashMap<String,Object>();
		
		try {
			
			dbUser = userService.selectUser(userVO.getUserId()) ;			
			
			if (dbUser != null && userVO.getUserId().trim().equals(dbUser.getUserId())) {

				if(userVO.getUserPw().trim().equals(dbUser.getUserPw())){
					
					loginMap.put("check", "SUCCESS");
					loginMap.put("dbUser", dbUser);
					
					session.setAttribute("dbUser", dbUser);
					logger.info(dbUser);
					logger.info(session.getAttribute("inviteGroupListNo"));
					
					if (session.getAttribute("inviteGroupListNo") != null) {
						loginMap.put("inviteGroupListNo","true");
					} else {
						loginMap.put("inviteGroupListNo", null);
					}
			
				} else {
					loginMap.put("check", "FAIL");
				}
				
			} else {
				loginMap.put("check", "FAIL");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginMap;
	}
	
	@RequestMapping(value = "/regist" ,method = RequestMethod.GET, produces = "application/json")
	public void regist() {

		logger.info("-------------------------------");
		logger.info("regist GET");
		logger.info("-------------------------------");
		
	}
	
	@RequestMapping(value = "/regist" ,method = RequestMethod.POST, produces = "application/json")
	public  @ResponseBody Map<String,String> regist(UserVO userVO) {

		logger.info("-------------------------------");
		logger.info("regist POST");
		logger.info("-------------------------------");
		
		try {
				dbUser = userService.selectUser(userVO.getUserId());
				logger.info("-------------------------------" + dbUser);
				
				if (dbUser == null) {
					
					userService.insertUser(userVO);
					checkMap.put("check", "SUCCESS");
					checkMap.put("idCheck", "SUCCESS");

				} else {
					checkMap.put("check", "FAIL");
					checkMap.put("idCheck", "FAIL");
				}
				
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkMap;
	}
	

	@RequestMapping(value = "/registIdCheck" ,method = RequestMethod.POST, produces = "application/json")
	public  @ResponseBody Map<String,String> registIdCheck(UserVO userVO) {

		logger.info("-------------------------------");
		logger.info("registIdCheck POST");
		logger.info("-------------------------------");
		
		try {
				dbUser = userService.selectUser(userVO.getUserId());
				logger.info("-------------------------------" + dbUser);
				
				if (dbUser == null) {

					checkMap.put("idCheck", "SUCCESS");

				} else {

					checkMap.put("idCheck", "FAIL");
				}
				
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkMap;
	}

	@RequestMapping(value = "/logout" ,method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String,String> logout(HttpSession session) {

		logger.info("-------------------------------");
		logger.info("logout GET");
		logger.info("-------------------------------");
		
		session.setAttribute("dbUser", null);
		session.setAttribute("inviteGroupListNo", null);
		session.invalidate();
		
		checkMap.put("check", "LOGOUT");
		
		return checkMap;
	}
	
	@RequestMapping(value = "/myPageView" ,method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody UserVO myPageView(HttpSession session){
		
		dbUser = (UserVO)session.getAttribute("dbUser");
		
		return dbUser;
	}
	
	@RequestMapping(value = "/updateUser" ,method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String,String> updateUser(UserVO userVO, HttpSession session){
		
		userVO.setUserId(((UserVO)session.getAttribute("dbUser")).getUserId());
		
		logger.info("-------------------------------");
		logger.info("updateUser POST");
		logger.info("-------------------------------");
		
		try {
			
			if(userVO.getUserPw().equals("")){
				
				userVO.setUserPw(((UserVO)session.getAttribute("dbUser")).getUserPw());
			
			}
			logger.info("굳굳굳굳굳굳굳굳굳굳굳굳굳굳굳"+userVO);
			
			userService.updateUser(userVO);
			
			checkMap.put("check", "SUCCESS");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkMap;
	}
	
	@Transactional
	@RequestMapping(value = "/deleteUser" ,method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String,String> deleteUser(HttpSession session){
		
		dbUser = ((UserVO)session.getAttribute("dbUser"));
		
		try {
		
			if (groupService.selectAllGroup(dbUser.getUserId()) != null) {
				GroupVO groupVO = new GroupVO();
				GroupListVO groupListVO = new GroupListVO();
				List<Integer> groupListNo = new ArrayList<Integer>();
				
				groupVO.setGroupUser(dbUser.getUserId());
				groupVO.setGroupUserGrade("groupMaster");
				groupListNo = groupService.selectGradeUser(groupVO);
				
				if (groupListNo != null) {
					
					for (int i = 0; i < groupListNo.size(); i++) {
					
						if(groupService.selectSecond(groupListNo.get(i)) == null){
							
							groupService.deleteGroup(groupListNo.get(i));
							groupListService.deleteGroup(groupListNo.get(i));
							
						} else {
							groupListVO.setGroupListNo(groupListNo.get(i));
							groupListVO.setGroupMaster(groupService.selectSecond(groupListNo.get(i)));
							groupListService.updateMaster(groupListVO);
							groupService.updateGrade(groupListVO);
							groupVO.setGroupListNo(groupListNo.get(i));
							groupService.deleteMember(groupVO);
						}
								
					}
				}
				
				groupService.deleteUserGroup(dbUser.getUserId());
						
			} 
			
			userService.deleteUser(dbUser.getUserId());
			
			checkMap.put("check", "SUCCESS");
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkMap;
	}
	
	@RequestMapping(value = "/selectGroupUserList" ,method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<UserVOAdd> selectGroupUserList(Integer groupListNo){
		
		List<UserVOAdd> userGroupList = new ArrayList<UserVOAdd>();
		
		try {
			userGroupList = userService.selectGroupUserList(groupListNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userGroupList;
	}

}
