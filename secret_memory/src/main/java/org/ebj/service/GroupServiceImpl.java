package org.ebj.service;

import java.util.List;

import javax.inject.Inject;

import org.ebj.mapper.GroupMapper;
import org.ebj.vo.GroupListVO;
import org.ebj.vo.GroupVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("GroupService")
public class GroupServiceImpl implements GroupService {
	
	@Inject
	private GroupMapper groupMapper;

	@Transactional
	@Override
	public void createGroup(GroupVO groupVO) throws Exception {
		groupMapper.createGroup(groupVO);
	}

	@Override
	public List<GroupListVO> selectGroup(String userId) throws Exception {
		return groupMapper.selectGroup(userId);
	}

	@Transactional
	@Override
	public void deleteGroup(Integer groupListNo) throws Exception {
		groupMapper.deleteGroup(groupListNo);
	}

	@Transactional
	@Override
	public void deleteMember(GroupVO groupVO) throws Exception {
		groupMapper.deleteMember(groupVO);
	}

	@Override
	public String selectSecond(Integer groupListNo) throws Exception {
		return groupMapper.selectSecond(groupListNo);
	}
	
	@Transactional
	@Override
	public void updateGrade(GroupListVO groupListVO) throws Exception {
		groupMapper.updateGrade(groupListVO);
	}

	@Transactional
	@Override
	public void insertGroupUser(GroupVO groupVO) throws Exception {
		groupMapper.insertGroupUser(groupVO);
		
	}

	@Override
	public GroupVO selectInviteGroup(GroupVO groupVO) throws Exception {
		return groupMapper.selectInviteGroup(groupVO);
	}

	@Override
	public List<Integer> selectGradeUser(GroupVO groupVO) throws Exception {
		return groupMapper.selectGradeUser(groupVO);
	}

	@Override
	public List<Integer> selectAllGroup(String userId) throws Exception {
		return groupMapper.selectAllGroup(userId);
	}

	@Override
	public void deleteUserGroup(String userId) throws Exception {
		groupMapper.deleteUserGroup(userId);
		
	}
}
