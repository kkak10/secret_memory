package org.ebj.service;

import org.ebj.vo.GroupListVO;

public interface GroupListService {

	public void createGroupList(GroupListVO groupListVO) throws Exception;
	
	public Integer selectGroupNo(String groupMaster) throws Exception;
	
	public String selectGroupMaster(GroupListVO groupListVO) throws Exception;	
	
	public void updateGroupName(GroupListVO groupListVO) throws Exception;
	
	public void updateMaster(GroupListVO groupListVO) throws Exception;
	
	public void deleteGroup(Integer groupListNo) throws Exception;

}
