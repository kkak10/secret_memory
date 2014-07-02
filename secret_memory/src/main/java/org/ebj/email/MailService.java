package org.ebj.email;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("MailService")
public class MailService {
	
	@Inject	
	private JavaMailSender mailSender;

	public void sendMail(String to, String subject, String text) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			InternetAddress[] mailAddress = {new InternetAddress(to)};
			message.setFrom(new InternetAddress("orgebj@gmail.com"));
			message.setSubject(subject, "UTF-8");
			String htmlContent = text;
			message.setText(htmlContent, "UTF-8", "html");
			message.setRecipients(RecipientType.TO, mailAddress);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}  
}