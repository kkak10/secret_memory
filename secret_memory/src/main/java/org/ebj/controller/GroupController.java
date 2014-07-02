package org.ebj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ebj.service.GroupListService;
import org.ebj.service.GroupService;
import org.ebj.vo.GroupListVO;
import org.ebj.vo.GroupVO;
import org.ebj.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/group")
public class GroupController {

	protected final static Logger logger = Logger.getLogger(GroupController.class);
	
	///Field
	List<GroupListVO> list = new ArrayList<GroupListVO>();
	List<GroupVO> groupList = new ArrayList<GroupVO>();
	Map<String,String> checkMap = new HashMap<String,String>();
	GroupVO groupVO = new GroupVO();
	
	@Inject
	private GroupService groupService;

	@Inject
	private GroupListService groupListService;

	@Transactional
	@RequestMapping(value="/createGroup", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody List<GroupListVO> createGroup(GroupListVO groupListVO, HttpSession session){
		logger.info("===================");
		logger.info("createGroup post");
		logger.info("===================");

		groupListVO.setGroupMaster(((UserVO)session.getAttribute("dbUser")).getUserId());

		try {
			
			if(groupListVO.getGroupName().equals("") == false){
				
				groupListService.createGroupList(groupListVO);

				Integer groupListNo = groupListService.selectGroupNo(((UserVO)session.getAttribute("dbUser")).getUserId());
				
				groupVO.setGroupListNo(groupListNo);
				groupVO.setGroupUser(((UserVO)session.getAttribute("dbUser")).getUserId());
				groupVO.setGroupUserGrade("groupMaster");
				groupService.createGroup(groupVO);
				
			}
			
			list = groupService.selectGroup(((UserVO)session.getAttribute("dbUser")).getUserId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	
	@RequestMapping(value="/updateGroupName", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody Map<String,String> updateGroup(GroupListVO groupListVO, HttpSession session){
		logger.info("===================");
		logger.info("updateGroupName post");
		logger.info("===================");

		try {
			groupListService.updateGroupName(groupListVO);
			checkMap.put("check", "SUCCESS");
			/*list = groupService.selectGroup(((UserVO)session.getAttribute("dbUser")).getUserId());*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkMap;
	}

	
	@RequestMapping(value="/deleteList", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody List<GroupListVO> deleteGroup(GroupListVO groupListVO){
		logger.info("===================");
		logger.info("deleteList post");
		logger.info("===================");

		try {
			//			list = groupService.selectGroup(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value="/selectGroupList", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody List<GroupListVO> selectGroupList(HttpSession session, UserVO userVO){
		logger.info("===================");
		logger.info("groupList post");
		logger.info("===================");

		try {
			if (userVO.getUserId() == null) {
				list = groupService.selectGroup(((UserVO)session.getAttribute("dbUser")).getUserId());
			} else {
				list = groupService.selectGroup(userVO.getUserId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/deleteMember", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody Map<String,String> deleteMember(GroupVO groupVO, HttpSession session){
		logger.info("===================");
		logger.info("deleteMember post");
		logger.info("===================");

		logger.info(groupVO);
		groupVO.setGroupUser(((UserVO)session.getAttribute("dbUser")).getUserId());
		
			try {
				groupService.deleteMember(groupVO);
				logger.info(groupVO + "삭제완료");
				checkMap.put("check", "SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return checkMap;
	}
	
	@Transactional
	@RequestMapping(value="/updateMaster", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody Map<String,String> updateMaster(GroupListVO groupListVO, HttpSession session){
		logger.info("===================");
		logger.info("updateMaster post");
		logger.info("===================");
		
		try {
			logger.info("처음에 받아온 groupLIstVO" + groupListVO);
			
			if(groupService.selectSecond(groupListVO.getGroupListNo()) == null){
				//그룹에 혼자 있을 경우
				groupService.deleteGroup(groupListVO.getGroupListNo());
				groupListService.deleteGroup(groupListVO.getGroupListNo());
			} else {
				//그룹에 혼자가 아닐경우
				groupListVO.setGroupMaster(groupService.selectSecond(groupListVO.getGroupListNo()));	//그룹에 두번째 사람에게 마스터 인계
				logger.info("마스터가 두번째 사람으로 바뀐 groupLIstVO" + groupListVO);
				groupListService.updateMaster(groupListVO);	//groupList에 마스터를 바꾼다.
				groupService.updateGrade(groupListVO);	//group에 멤버를 마스터로 바꾼다.
				groupVO.setGroupListNo(groupListVO.getGroupListNo());	//그룹번호를 groupVO에 넣어준다.
				groupVO.setGroupUser(((UserVO)session.getAttribute("dbUser")).getUserId());	//로그인한 아이디를 groupVO에 넣는다.
				groupService.deleteMember(groupVO);
				logger.info("group에서 나가는 groupVO" + groupVO);
				checkMap.put("check", "CHANGE");
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkMap;
	}
	
	
	@RequestMapping(value="/insertGroupUser", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody Map<String,String> insertGroupUser(HttpSession session){
		
		logger.info("====================");
		logger.info("insertGroupUser get");
		logger.info("====================");
		
		if (session.getAttribute("inviteGroupListNo") != null) {
			groupVO.setGroupListNo(Integer.parseInt((String)session.getAttribute("inviteGroupListNo")));
		}
		groupVO.setGroupUser(((UserVO)session.getAttribute("dbUser")).getUserId());
		groupVO.setGroupUserGrade("groupMember");
		
		try {
			if (groupService.selectInviteGroup(groupVO) == null) {
				groupService.createGroup(groupVO);
				checkMap.put("check", "SUCCESS");
			} else {
				checkMap.put("check", "FAIL");
			}
			
			session.setAttribute("inviteGroupListNo", null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return checkMap;
	}
	
	@RequestMapping(value="/selectMaster", method={RequestMethod.POST}, produces="application/json")
	public @ResponseBody Map<String,String> selectMaster(GroupListVO groupListVO, HttpSession session){
		logger.info("===================");
		logger.info("selectMaster post");
		logger.info("===================");

		logger.info(groupListVO);
		groupListVO.setGroupMaster(((UserVO)session.getAttribute("dbUser")).getUserId());
		
			try {
				String groupMaster = groupListService.selectGroupMaster(groupListVO);
				if(groupMaster != null && groupMaster != " "){
					checkMap.put("check", "SUCCESS");
				}else{
					checkMap.put("check", "FAIL");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return checkMap;
	}
}