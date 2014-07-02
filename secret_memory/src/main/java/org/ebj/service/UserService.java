package org.ebj.service;

import java.util.List;

import org.ebj.vo.UserVO;
import org.ebj.vo.UserVOAdd;

public interface UserService {
	
	public void insertUser(UserVO userVO) throws Exception;
	
	public UserVO selectUser(String userId) throws Exception;
	
	public List<UserVOAdd> selectGroupUserList(Integer groupListNo) throws Exception;
	
	public void updateUser(UserVO userVO) throws Exception;
		
	public void deleteUser(String userId) throws Exception;
}
