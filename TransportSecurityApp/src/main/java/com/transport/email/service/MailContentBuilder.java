package com.transport.email.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
@Service
public class MailContentBuilder {
	public static final Logger LOG = LoggerFactory.getLogger(MailContentBuilder.class);
	private TemplateEngine templateEngine;

	@Autowired
	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String regbuild(String recipient, String name, String setPasswordURL) {
		LOG.debug(setPasswordURL);
		Context context = new Context();
		context.setVariable("recipient", recipient);
		context.setVariable("name", name);
		context.setVariable("setPasswordURL", setPasswordURL);
		return templateEngine.process(new ClassPathResource("regmailtemplate").getPath(), context);
	}

	public String otpbuild(String secretKey, String recipient) {
		Context context = new Context();
		context.setVariable("secretKey", secretKey);
		return templateEngine.process(new ClassPathResource("otpmailtemplate").getPath(), context);
	}

	public String resetbuild(String emailId, String activationURL) {
		Context context = new Context();
		context.setVariable("emailId", emailId);
		context.setVariable("resetURL", activationURL);
		return templateEngine.process(new ClassPathResource("resetmailtemplate").getPath(), context);
	}

	public String kycApprovedbuild(String emailId) {
		Context context = new Context();
		context.setVariable("emailId", emailId);
		return templateEngine.process(new ClassPathResource("kycapproved").getPath(), context);
	}

	public String kycRejectedbuild(String emailId) {
		Context context = new Context();
		context.setVariable("emailId", emailId);
		return templateEngine.process(new ClassPathResource("kycrejected").getPath(), context);
	}
}
