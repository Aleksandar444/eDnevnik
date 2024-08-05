package com.example.schoolBE.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.schoolBE.email.EmailObject;
import com.example.schoolBE.services.EmailService;

@RestController
@RequestMapping(value="/email")
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	
	private static String PATH_TO_ATTACHMENT = "C:\\Users\\salle\\Desktop\\backendE-dnevnik.png";
	
	@RequestMapping(method = RequestMethod.POST, value = "/simpleEmail")
	public String sendSimpleMessage(@RequestBody EmailObject object) {
		LocalDateTime currentTime = LocalDateTime.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String time = currentTime.format(formatter);
		logger.info("E-poruka je uspesno poslata roditelju (ime,prezime,email): vremeSlanja = {}",object,time);
		if(object==null || object.getTo()==null || object.getText()==null) {
			return "Email not sent.";
		}
		emailService.sendSimpleMessage(object);
		return "Your mail has been sent!";
	}
	@RequestMapping(method = RequestMethod.POST, value ="/templateEmail")
	public String sendTemplateMessage(@RequestBody EmailObject object) throws Exception {
		logger.info("E-poruka (template) je uspesno poslata");
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailService.sendTemplateMessage(object);
		return "Your mail has been sent!";
	}
	@RequestMapping(method = RequestMethod.POST, value ="/emailWithAttachment")
	public String sendMessageWithAttachment(@RequestBody EmailObject object) throws Exception{
		logger.info("E-poruka (attachment) je uspesno poslata");
		if (object == null || object.getTo() == null || object.getText() == null) {
			return null;
		}
		emailService.sendMessageWithAttachment(object, PATH_TO_ATTACHMENT);
		return "Your mail has been sent!";
	}
}
