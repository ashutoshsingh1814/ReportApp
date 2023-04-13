package com.ashu.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender mailsender;
	
	public boolean sendMail(String subject,String body,String to, File f) {
		 
		try {
			MimeMessage mimeMessage = mailsender.createMimeMessage();
			 
			 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			 helper.setSubject(subject);
			 helper.setText(body,true);
			 helper.setTo(to);
			 helper.addAttachment("plan-infoexcel", f);
			 mailsender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return false;
		
	}

}
