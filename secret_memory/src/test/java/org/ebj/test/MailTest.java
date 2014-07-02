package org.ebj.test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.ebj.email.MailService;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/**/*-context.xml"})
public class MailTest{

	protected static Logger logger = Logger.getLogger(JDBCTest.class);

	@Inject
	private MailService mailService;

	@Test
	public void sendMail() {
		
		String addMail = "sonsb847@gmail.com";
		
		List<String> mails = new ArrayList<String>();
		
		String[] temp = addMail.split(";");
		
		String mailTextStart = "<a href='http://localhost:8080/resources/index.html?groupListNo=3'>";
		String mailTextEnd	="</a>";
		for(int i = 0; i < temp.length; i++){
			mails.add(temp[i]);
			
			mailService.sendMail(mails.get(i), "회원가입 테스트", mailTextStart + "SecretMemory입니다." + mailTextEnd);
			
		}
	}
}