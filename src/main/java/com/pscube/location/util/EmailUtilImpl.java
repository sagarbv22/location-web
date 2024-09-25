package com.pscube.location.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtilImpl implements EmailUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String toAddress, String subject, String body) {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(body);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMailSender.send(message);
	}

}
