package org.ebj.test;

import javax.inject.Inject;

import org.ebj.service.UserService;
import org.ebj.vo.UserVO;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
public class UserTest {
	
	protected static Logger logger = Logger.getLogger(UserTest.class);
	
	@Inject
	UserService userService;
	
	UserVO user = new UserVO("test","test","test","test");
	
//	@Test
	public void insertUserTest(){
		
		try {
			
			userService.insertUser(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void selectUserTest(){
		
		try {
			
			String userId = "test";
			logger.info("==========================");
			logger.info(userService.selectUser(userId));
			logger.info("==========================");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void selectGroupUserList(){
		
		try {
			logger.info("==========================");
			logger.info(userService.selectGroupUserList(1));
			logger.info("==========================");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
