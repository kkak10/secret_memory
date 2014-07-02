package org.ebj.test;

import javax.inject.Inject;

import org.ebj.service.GroupService;
import org.ebj.vo.GroupVO;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
public class GroupServiceTest {

	static Logger logger = Logger.getLogger(GroupServiceTest.class);
	
	@Inject
	GroupService groupService;
	
//	@Test
	public void selectGroupTest() throws Exception{
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
		logger.info(groupService.selectGroup("kjh"));
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!");
		
	}
	
//	@Test
	public void insertGroupTest() throws Exception{
		
		GroupVO groupVO = new GroupVO(12, "kjh" ,"groupMaster");
		
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		groupService.createGroup(groupVO);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
	}
	
//	@Test
	public void deleteGroupTest() throws Exception{
		
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		groupService.deleteGroup(12);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
	}
//	@Test
	public void deleteMemberTest() throws Exception{
		GroupVO groupVO = new GroupVO(12,"kjh","groupMaster");
		
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		groupService.deleteMember(groupVO);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
	}
	
//	@Test
	public void selectSecond() throws Exception{
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//String ar = groupService.selectSecond(61);
		if (groupService.selectSecond(63) == null) {
			logger.info("1111111111111111111111111111");
		} else {
			logger.info("2222222222222222222222222222");
		}
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	@Test
	public void selectInviteGroup() throws Exception{
		GroupVO groupVO = new GroupVO(8,"psm");
		
		if (groupService.selectInviteGroup(groupVO) != null) {
			logger.info("1111111111111111111111111111");
		} else {
			logger.info("2222222222222222222222222222");
		}
	}
}
