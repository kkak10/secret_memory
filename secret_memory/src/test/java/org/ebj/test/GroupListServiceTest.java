package org.ebj.test;

import javax.inject.Inject;

import org.ebj.service.GroupListService;
import org.ebj.vo.GroupListVO;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
public class GroupListServiceTest {
	
	static Logger logger = Logger.getLogger(GroupServiceTest.class);
	
	@Inject
	GroupListService groupListService;
	
//	@Test
	public void insertTest() throws Exception{
		GroupListVO groupListVO = new GroupListVO("ssb","test");
		
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
		groupListService.createGroupList(groupListVO);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
//	@Test
	public void updateTest() throws Exception{
		GroupListVO groupListVO = new GroupListVO("ssb","test","test123");
		
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
		groupListService.updateGroupName(groupListVO);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
//	@Test
	public void deleteTest() throws Exception{
		GroupListVO groupListVO = new GroupListVO("ssb","test123");
		
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
		groupListService.deleteGroup(groupListVO.getGroupListNo());
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
//	@Test
	public void updateMaster() throws Exception{
		GroupListVO groupListVO = new GroupListVO("ssb","test");
		
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
		groupListService.updateMaster(groupListVO);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	@Test
	public void selectMaster() throws Exception{
		GroupListVO groupListVO = new GroupListVO("lhu",10);
		
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
		groupListService.selectGroupMaster(groupListVO);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
	}
}
