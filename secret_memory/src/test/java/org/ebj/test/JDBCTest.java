package org.ebj.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
public class JDBCTest {
	
	protected static Logger logger = Logger.getLogger(JDBCTest.class);
	
	@Inject
	DataSource dataSource;
	
	@Test
	public void dsTest(){
		try {
			Connection conn = null;
			conn = dataSource.getConnection();
			
			logger.info("-----------------------");
			logger.info(conn);
			logger.info("-----------------------");
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
