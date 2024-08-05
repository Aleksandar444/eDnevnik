package com.example.schoolBE.services;

import com.example.schoolBE.email.EmailObject;

public interface EmailService {
	void sendSimpleMessage (EmailObject object);
	void sendTemplateMessage (EmailObject object) throws
	Exception;
	void sendMessageWithAttachment (EmailObject object,
	String pathToAttachment) throws Exception;
	void sendEmailToParent(String studentName, String parentEmail,String subject,String message);
}
