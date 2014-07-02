package org.ebj.test;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
public class SpringTest {
	
	protected static Logger logger = Logger.getAnonymousLogger();
	
	@Inject
	String springTest;
	
	@Test
	public void spTest(){
		logger.info("-----------------------");
		logger.info(springTest);
		logger.info("-----------------------");
	}
}
