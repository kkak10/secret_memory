package org.ebj.service;

import javax.inject.Inject;

import org.ebj.mapper.GroupListMapper;
import org.ebj.vo.GroupListVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("GroupListService")
public class GroupListServiceImpl implements GroupListService{

	@Inject
	private GroupListMapper groupListMapper;
	
	@Override
	public Integer selectGroupNo(String groupMaster) throws Exception {
		return groupListMapper.selectGroupNo(groupMaster);
	}
	
	@Transactional
	@Override
	public void createGroupList(GroupListVO groupListVO) throws Exception {
		groupListMapper.createGroupList(groupListVO);
	}

	@Transactional
	@Override
	public void updateGroupName(GroupListVO groupListVO) throws Exception {
		groupListMapper.updateGroupName(groupListVO);
	}

	@Transactional
	@Override
	public void deleteGroup(Integer groupListNo) throws Exception {
		groupListMapper.deleteGroup(groupListNo);
	}
	
	@Transactional
	@Override
	public void updateMaster(GroupListVO groupListVO) throws Exception {
		groupListMapper.updateMaster(groupListVO);
	}
	
	@Override
	public String selectGroupMaster(GroupListVO groupListVO) throws Exception {
		return groupListMapper.selectGroupMaster(groupListVO);
	}
}
