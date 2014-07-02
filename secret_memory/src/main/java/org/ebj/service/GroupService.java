package org.ebj.service;

import java.util.List;

import org.ebj.vo.GroupListVO;
import org.ebj.vo.GroupVO;

public interface GroupService {

	public void createGroup(GroupVO groupVO) throws Exception;
	
	public void insertGroupUser(GroupVO groupVO) throws Exception;
	
	public GroupVO selectInviteGroup(GroupVO groupVO) throws Exception;
	
	public String selectSecond(Integer groupListNo) throws Exception;
	
	public List<GroupListVO> selectGroup(String userId) throws Exception;  
	
	public List<Integer> selectAllGroup(String userId) throws Exception;
	
	public List<Integer> selectGradeUser(GroupVO groupVO) throws Exception;
	
	public void updateGrade(GroupListVO groupListVO) throws Exception;
	
	public void deleteGroup(Integer groupListNo) throws Exception;
	
	public void deleteMember(GroupVO groupVO) throws Exception;
	
	public void deleteUserGroup(String userId) throws Exception;

}
