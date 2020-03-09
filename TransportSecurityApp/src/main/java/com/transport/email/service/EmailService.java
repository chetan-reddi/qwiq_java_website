package com.transport.email.service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.transport.constant.TransportErrorMessages;
import com.transport.exception.SendOTPFailed;

@Service
public class EmailService {
	private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);
	private JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	@Autowired
	MailContentBuilder mailContentBuilder;

	

	public void sendOTP(String recipient, String secretKey) throws IOException, SendOTPFailed {
		
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			//messageHelper.setFrom("team@qwiqmove.co.uk");
			messageHelper.setFrom("support@blurover.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject("OTP Mail");
			String content = mailContentBuilder.otpbuild(secretKey, recipient);
			messageHelper.setText(content, true);
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			LOG.debug("Error occured : "+e.getMessage());
			throw new SendOTPFailed(TransportErrorMessages.OTP_FAILED);
		}
	}



	public void resetMail(String emailId, String resetURL) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	        messageHelper.setFrom("support@blurover.com");
	        messageHelper.setTo(emailId);
	        messageHelper.setSubject("Reset Password Mail");
	        String content = mailContentBuilder.resetbuild(emailId,resetURL);
	        messageHelper.setText(content, true);
	    };
	    try {
	        mailSender.send(messagePreparator);
	    } catch (MailException e) {
	        // runtime exception; compiler will not force you to handle it
	    }
			
		}
				
		
	}

