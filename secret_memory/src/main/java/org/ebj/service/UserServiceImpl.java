package org.ebj.service;

import java.util.List;

import javax.inject.Inject;

import org.ebj.mapper.UserMapper;
import org.ebj.vo.UserVO;
import org.ebj.vo.UserVOAdd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserMapper userMapper;
	
	@Transactional
	@Override
	public void insertUser(UserVO userVO) throws Exception {
		
		userMapper.insertUser(userVO);
	}

	@Override
	public UserVO selectUser(String userId) throws Exception {
		
		return userMapper.selectUser(userId);
	}

	@Override
	public List<UserVOAdd> selectGroupUserList(Integer groupListNo) throws Exception {

		return userMapper.selectGroupUserList(groupListNo);
	}
	
	@Transactional
	@Override
	public void updateUser(UserVO userVO) throws Exception {

		userMapper.updateUser(userVO);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		
		userMapper.deleteUser(userId);
	}
}
